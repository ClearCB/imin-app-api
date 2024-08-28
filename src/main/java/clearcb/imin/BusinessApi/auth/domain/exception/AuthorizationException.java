package clearcb.imin.BusinessApi.auth.domain.exception;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String errorMessage) {
        super(errorMessage);
    }
}
