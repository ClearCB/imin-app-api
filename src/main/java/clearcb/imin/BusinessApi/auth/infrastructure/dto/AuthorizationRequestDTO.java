package clearcb.imin.BusinessApi.auth.infrastructure.dto;

import clearcb.imin.BusinessApi.common.infrastructure.validation.UUIDConstraint;
import jakarta.validation.constraints.NotBlank;

public record AuthorizationRequestDTO(
        @NotBlank(message = "Property actor Id must be valid UUID") @UUIDConstraint String actorId,
        @NotBlank(message = "Property object Id must be valid UUID") @UUIDConstraint String objectId,
        @NotBlank(message = "Property action Id must be valid UUID") @UUIDConstraint String actionId) {
}
