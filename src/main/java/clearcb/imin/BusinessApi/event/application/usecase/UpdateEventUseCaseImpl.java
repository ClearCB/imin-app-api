package clearcb.imin.BusinessApi.event.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventNotFoundError;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.EventMessageEnum;
import clearcb.imin.BusinessApi.event.domain.port.input.UpdateEventUseCase;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;
import clearcb.imin.BusinessApi.event.domain.validator.EventValidator;

import java.util.UUID;

public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    EventRepositoryPort eventRepositoryPort;
    Logger logger;
    EventValidator eventValidator;

    public UpdateEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, Logger logger, EventValidator eventValidator) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.logger = logger;
        this.eventValidator = eventValidator;
    }


    @Override
    public Event update(Event event) {

        // Ensure that event is valid to update
        ensureEventIsValid(event);

        // Ensure even exist
        ensureEventExist(event.getId());

        // Update event
        Event eventUpdated = updateEvent(event);

        // Log information about the update
        logUpdateInformation(eventUpdated);

        // return the updated event
        return eventUpdated;
    }

    private void ensureEventExist(UUID eventId) {
        eventRepositoryPort.getEventById(eventId)
                .orElseThrow(() -> new EventNotFoundError(EventMessageEnum.EVENT_NOT_FOUND.getMessage()));
    }

    private void logUpdateInformation(Event eventUpdated) {
        logger.info(EventMessageEnum.EVENT_UPDATE_OK.getMessage());
        logger.info("Event updated: " + eventUpdated.toString());
    }

    private Event updateEvent(Event event) {
        return eventRepositoryPort.updateEvent(event);
    }

    private void ensureEventIsValid(Event event) {
        eventValidator.validate(event);
    }

}
