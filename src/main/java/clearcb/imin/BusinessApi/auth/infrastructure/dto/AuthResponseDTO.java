package clearcb.imin.BusinessApi.auth.infrastructure.dto;

import clearcb.imin.BusinessApi.auth.domain.model.UserData;

public record AuthResponseDTO(String message, boolean result, UserData userData) {
}
