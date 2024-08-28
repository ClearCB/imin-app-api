package clearcb.imin.BusinessApi.common.domain.exception;

public class InvalidUUIDFormatException extends RuntimeException {
    public InvalidUUIDFormatException(String errorMessage) {
        super(errorMessage);
    }
}
