package clearcb.imin.BusinessApi.auth.domain.model;

public record RegisterRequest(String username, String password, String confirmationPassword ,String email) {
}
