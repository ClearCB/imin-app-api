package clearcb.imin.BusinessApi.event.domain.exceptions;

public class EventValidationError extends RuntimeException {
    public EventValidationError(String errorMessage) {
        super(errorMessage);
    }
}


