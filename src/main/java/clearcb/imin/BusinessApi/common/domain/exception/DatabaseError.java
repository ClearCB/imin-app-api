package clearcb.imin.BusinessApi.common.domain.exception;

public class DatabaseError extends RuntimeException {
    public DatabaseError(String errorMessage) {
        super(errorMessage);
    }
}


