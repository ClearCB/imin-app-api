package clearcb.imin.BusinessApi.event.domain.validator;

import clearcb.imin.BusinessApi.event.domain.exceptions.EventValidationError;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.EventMessageEnum;

public class EventValidatorImpl implements EventValidator {

    public EventValidatorImpl() {
    }

    @Override
    public void validate(Event event) {
        if (event == null) {
            throw new EventValidationError(EventMessageEnum.EVENT_CANT_BE_NULL.getMessage());
        }
        validateTitle(event);
    }

    private void validateTitle(Event event) {
        if (event.getTitle() == null || event.getTitle().isEmpty()) {
            throw new EventValidationError(EventMessageEnum.EVENT_TITLE_CANT_BE_NULL.getMessage());
        }
    }

}
