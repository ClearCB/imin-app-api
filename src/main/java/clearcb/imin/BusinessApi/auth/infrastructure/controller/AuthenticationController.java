package clearcb.imin.BusinessApi.auth.infrastructure.controller;

import clearcb.imin.BusinessApi.auth.domain.model.*;
import clearcb.imin.BusinessApi.auth.domain.port.input.LoginUseCase;
import clearcb.imin.BusinessApi.auth.domain.port.input.RegisterUseCase;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.AuthResponseDTO;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.LoginRequestDTO;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.RegisterRequestDTO;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.UserEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.mapper.AuthenticationMapper;
import clearcb.imin.BusinessApi.auth.infrastructure.repository.JpaUserRepository;
import clearcb.imin.BusinessApi.auth.infrastructure.usecase.RegisterUseCaseImpl;
import clearcb.imin.BusinessApi.common.domain.exception.CustomBadCredentialsException;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.email.infrastructure.service.EmailServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final JpaUserRepository jpaUserRepository;
    private final EmailServiceImpl emailService;
    private final Logger logger;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {

        boolean isEnabledUser = isEnabledUser(request.username());
        if (!isEnabledUser){
            AuthResponse authResponse = new AuthResponse("Not enabled user", false, new UserData(null, request.username(),null, null, false));
            return ResponseEntity.status(401).body(AuthenticationMapper.toDto(authResponse));
        }


        LoginRequest loginRequest = AuthenticationMapper.toDomain(request);
        AuthResponse authResponse = loginUseCase.login(loginRequest);

        logger.info("Login succeed:" + loginRequest);
        return ResponseEntity.ok(AuthenticationMapper.toDto(authResponse));

    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request,  HttpServletRequest requestServlet) {

        if (!request.password().equals(request.confirmationPassword())) {
            throw new CustomBadCredentialsException(AuthorizatorMessageEnum.BAD_CREDENTIALS.getMessage());
        }

        RegisterRequest registerRequest = AuthenticationMapper.toDomain(request);
        AuthResponse authResponse = registerUseCase.register(registerRequest);

        Optional<UserEntity> userEntityOptional = jpaUserRepository.findById(UUID.fromString(authResponse.userData().id()));

        if (userEntityOptional.isPresent()){
            try {
                this.sendEmailVerification(userEntityOptional.get(),getSiteURL(requestServlet));
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        logger.info("Register succeed:" + request);
        return ResponseEntity.ok(AuthenticationMapper.toDto(authResponse));

    }

    @PostMapping(value = "send-verification")
    public void sendVerifyEmail(@RequestBody @Valid String username,  HttpServletRequest requestServlet) {

        Optional<UserEntity> userEntityOptional = jpaUserRepository.findByUsername(username);

        if (userEntityOptional.isPresent()){
            try {
                UserEntity user = userEntityOptional.get();
                int yourStringLength = 40;
                String generatedString = new Random().ints(97, 123)
                        .limit(yourStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                user.setVerificationCode(generatedString);

                jpaUserRepository.save(user);
                this.sendEmailVerification(user,getSiteURL(requestServlet));
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

    }


    @PostMapping(value = "validate-token")
    public void validateToken() {

    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    private void sendEmailVerification(UserEntity userEntity, String siteURL) throws MessagingException, UnsupportedEncodingException {
        emailService.sendVerificationEmail(userEntity,siteURL);
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        RegisterUseCaseImpl r = (RegisterUseCaseImpl) registerUseCase;
        if (r.verify(code)) {

            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    private boolean isEnabledUser(String username){

        Optional<UserEntity> userEntityOptional = jpaUserRepository.findByUsername(username);

        return userEntityOptional.map(UserEntity::isEnabled).orElse(false);

    }
}