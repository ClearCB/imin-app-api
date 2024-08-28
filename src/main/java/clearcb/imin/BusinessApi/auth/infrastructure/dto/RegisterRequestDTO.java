package clearcb.imin.BusinessApi.auth.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(@NotBlank String username, @NotBlank String password,
                                 @NotBlank String confirmationPassword,@NotBlank String email) {
}
