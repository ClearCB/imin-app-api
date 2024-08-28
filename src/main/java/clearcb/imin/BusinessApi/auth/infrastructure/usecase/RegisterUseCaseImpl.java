package clearcb.imin.BusinessApi.auth.infrastructure.usecase;

import clearcb.imin.BusinessApi.auth.domain.model.*;
import clearcb.imin.BusinessApi.auth.domain.port.input.RegisterUseCase;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.UserEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.repository.JpaUserRepository;
import clearcb.imin.BusinessApi.common.domain.exception.CustomBadCredentialsException;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegisterUseCaseImpl implements RegisterUseCase {

    private final JpaUserRepository jpaUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Logger logger;

    @Override
    public AuthResponse register(RegisterRequest request) {

        try {

            UserEntity user = UserEntity.builder()
                    .username(request.username())
                    .password(passwordEncoder.encode(request.password()))
                    .role(AppRole.USER)
                    .email(request.email())
                    .build();

            int yourStringLength = 40;
            String generatedString = new Random().ints(97, 123)
                    .limit(yourStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            user.setVerificationCode(generatedString);

            jpaUserRepository.save(user);

            logger.info("User registered: " + user.getUsername());

            Optional<UserEntity> userEntityOptional = jpaUserRepository.findByUsername(user.getUsername());

            UUID userId;
            if (userEntityOptional.isPresent()) {
                userId = userEntityOptional.get().getId();
                String email = userEntityOptional.get().getEmail();
                return new AuthResponse(AuthorizatorMessageEnum.REGISTER_OK.getMessage(), true, new UserData(userId.toString(), user.getUsername(), jwtService.getToken(user), email,userEntityOptional.get().isEnabled()));
            } else {
                throw new DataIntegrityViolationException("");
            }


        } catch (DataIntegrityViolationException e) {

            logger.error("Register error: " + e.getMessage());

            if (e.getCause() instanceof ConstraintViolationException) {
                String errorMessage = "Username or email already exist.";
                throw new CustomBadCredentialsException(errorMessage);
            } else {
                throw new CustomBadCredentialsException(AuthorizatorMessageEnum.BAD_CREDENTIALS.getMessage());
            }

        }



    }

    public boolean verify(String verificationCode) {
        UserEntity user = jpaUserRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            jpaUserRepository.save(user);

            return true;
        }

    }
}
