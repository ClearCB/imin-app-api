package clearcb.imin.BusinessApi.auth.domain.model;

public record UserData(String id, String username, String token, String email, boolean enabled) {
}