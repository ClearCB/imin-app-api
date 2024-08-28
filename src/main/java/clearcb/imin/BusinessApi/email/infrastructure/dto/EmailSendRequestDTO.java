package clearcb.imin.BusinessApi.email.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailSendRequestDTO(@NotBlank String to, String cc, @NotBlank String subject,
                                  @NotBlank String message) {
}
