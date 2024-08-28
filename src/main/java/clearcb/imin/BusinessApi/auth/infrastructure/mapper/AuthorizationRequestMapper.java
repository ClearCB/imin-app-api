package clearcb.imin.BusinessApi.auth.infrastructure.mapper;

import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationRequest;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationResponse;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.AuthorizationRequestDTO;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.AuthorizationResponseDTO;

import java.util.UUID;

public class AuthorizationRequestMapper {

    private AuthorizationRequestMapper() {
    }

    public static AuthorizationRequest toDomain(AuthorizationRequestDTO authorizationRequestDTO) {
        return new AuthorizationRequest(
                UUID.fromString(authorizationRequestDTO.actorId()),
                UUID.fromString(authorizationRequestDTO.objectId()),
                UUID.fromString(authorizationRequestDTO.actionId())
        );
    }

    public static AuthorizationResponseDTO toDTO(AuthorizationResponse authorizationResponse) {
        return new AuthorizationResponseDTO(
                authorizationResponse.result(),
                authorizationResponse.message()
        );
    }
}
