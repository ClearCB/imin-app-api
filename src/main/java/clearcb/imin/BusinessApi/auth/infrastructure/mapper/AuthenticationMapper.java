package clearcb.imin.BusinessApi.auth.infrastructure.mapper;

import clearcb.imin.BusinessApi.auth.domain.model.AuthResponse;
import clearcb.imin.BusinessApi.auth.domain.model.LoginRequest;
import clearcb.imin.BusinessApi.auth.domain.model.RegisterRequest;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.AuthResponseDTO;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.LoginRequestDTO;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.RegisterRequestDTO;

public class AuthenticationMapper {

    private AuthenticationMapper() {
    }

    public static LoginRequest toDomain(LoginRequestDTO loginRequestDTO) {
        return new LoginRequest(loginRequestDTO.username(), loginRequestDTO.password());
    }

    public static RegisterRequest toDomain(RegisterRequestDTO registerRequestDTO) {
        return new RegisterRequest(registerRequestDTO.username(), registerRequestDTO.password(), registerRequestDTO.confirmationPassword(), registerRequestDTO.email());
    }

    public static AuthResponseDTO toDto(AuthResponse authResponse) {
        return new AuthResponseDTO(authResponse.message(), authResponse.result(), authResponse.userData());
    }

}
