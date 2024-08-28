package clearcb.imin.BusinessApi.event.domain.exceptions;

public class EventNotFoundError extends RuntimeException {
    public EventNotFoundError(String errorMessage) {
        super(errorMessage);
    }
}


