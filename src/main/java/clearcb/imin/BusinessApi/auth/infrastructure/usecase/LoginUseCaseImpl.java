package clearcb.imin.BusinessApi.auth.infrastructure.usecase;

import clearcb.imin.BusinessApi.auth.domain.model.AuthResponse;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizatorMessageEnum;
import clearcb.imin.BusinessApi.auth.domain.model.LoginRequest;
import clearcb.imin.BusinessApi.auth.domain.model.UserData;
import clearcb.imin.BusinessApi.auth.domain.port.input.LoginUseCase;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.UserEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.repository.JpaUserRepository;
import clearcb.imin.BusinessApi.common.domain.exception.CustomBadCredentialsException;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final JpaUserRepository jpaUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Logger logger;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

            UserDetails user = jpaUserRepository.findByUsername(loginRequest.username()).orElseThrow();
            String token = jwtService.getToken(user);

            logger.info("User logged in: " + user.getUsername());

            Optional<UserEntity> userEntityOptional = jpaUserRepository.findByUsername(user.getUsername());

            UUID userId;
            if (userEntityOptional.isPresent()) {
                userId = userEntityOptional.get().getId();
                String email = userEntityOptional.get().getEmail();
                return new AuthResponse(AuthorizatorMessageEnum.LOGIN_OK.getMessage(), true, new UserData(userId.toString(), user.getUsername(), token, email,userEntityOptional.get().isEnabled()));
            } else {
                throw new BadCredentialsException("");
            }

        } catch (BadCredentialsException e) {

            logger.info("User login error: " + e.getMessage());
            throw new CustomBadCredentialsException(AuthorizatorMessageEnum.BAD_CREDENTIALS.getMessage());

        }

    }
}
