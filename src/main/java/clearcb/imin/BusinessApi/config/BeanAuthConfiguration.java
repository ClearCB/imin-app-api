package clearcb.imin.BusinessApi.config;

import clearcb.imin.BusinessApi.auth.application.service.AuthorizationContextManager;
import clearcb.imin.BusinessApi.auth.application.usecase.CommunityAuthorizeUseCaseImpl;
import clearcb.imin.BusinessApi.auth.application.usecase.EventAuthorizeUseCaseImpl;
import clearcb.imin.BusinessApi.auth.domain.model.ActionContextEnum;
import clearcb.imin.BusinessApi.auth.domain.port.input.AuthorizeUseCase;
import clearcb.imin.BusinessApi.auth.domain.port.output.AuthorizatorRepositoryPort;
import clearcb.imin.BusinessApi.auth.infrastructure.repository.JpaUserRepository;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class BeanAuthConfiguration {

    private final JpaUserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not fournd"));
    }

    @Bean
    public AuthorizeUseCase authorizeUseCaseEventImplementation(AuthorizatorRepositoryPort authorizatorRepositoryPort, Logger logger) {
        return new EventAuthorizeUseCaseImpl(authorizatorRepositoryPort, logger);
    }

    @Bean
    public AuthorizeUseCase authorizeUseCaseCommunityImplementation(AuthorizatorRepositoryPort authorizatorRepositoryPort, Logger logger) {
        return new CommunityAuthorizeUseCaseImpl(authorizatorRepositoryPort, logger);
    }

    @Bean
    public AuthorizationContextManager authorizationContextManager(AuthorizeUseCase authorizeUseCaseEventImplementation, AuthorizeUseCase authorizeUseCaseCommunityImplementation, Logger logger) {
        Map<ActionContextEnum, AuthorizeUseCase> strategies = new HashMap<>();
        strategies.put(ActionContextEnum.EVENT, authorizeUseCaseEventImplementation);
        strategies.put(ActionContextEnum.COMMUNITY, authorizeUseCaseCommunityImplementation);
        return new AuthorizationContextManager(strategies, logger);
    }

}