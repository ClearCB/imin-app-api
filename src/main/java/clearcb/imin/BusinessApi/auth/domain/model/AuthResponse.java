package clearcb.imin.BusinessApi.auth.domain.model;

public record AuthResponse(String message, boolean result, UserData userData) {
}
