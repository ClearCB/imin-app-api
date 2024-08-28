package clearcb.imin.BusinessApi.auth.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank String username, @NotBlank String password) {
}
