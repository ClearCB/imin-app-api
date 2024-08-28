package clearcb.imin.BusinessApi.common.domain.exception;

public class CustomBadCredentialsException extends RuntimeException {
    public CustomBadCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
