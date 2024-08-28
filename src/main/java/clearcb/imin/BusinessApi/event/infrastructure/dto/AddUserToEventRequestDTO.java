package clearcb.imin.BusinessApi.event.infrastructure.dto;

import clearcb.imin.BusinessApi.common.infrastructure.validation.UUIDConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

public record AddUserToEventRequestDTO(@UUIDConstraint String userId, @UUIDConstraint String eventId) {
}