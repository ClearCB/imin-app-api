package clearcb.imin.BusinessApi.event.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventNotFoundError;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.EventMessageEnum;
import clearcb.imin.BusinessApi.event.domain.port.input.CreateEventUseCase;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;
import clearcb.imin.BusinessApi.event.domain.validator.EventValidator;

import java.util.Optional;

public class CreateEventUseCaseImpl implements CreateEventUseCase {

    EventRepositoryPort eventRepositoryPort;
    Logger logger;

    EventValidator eventValidator;

    public CreateEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, Logger logger, EventValidator eventValidator) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.logger = logger;
        this.eventValidator = eventValidator;
    }

    @Override
    public Event create(Event event) {

        // Ensure event is valid
        validateEvent(event);

        // Create event
        Optional<Event> eventOptionalCreated = createEvent(event);

        if (eventOptionalCreated.isEmpty()) {
            throw new EventNotFoundError(EventMessageEnum.EVENT_NOT_FOUND.getMessage());
        }

        Event eventCreated = eventOptionalCreated.get();

        // Log creation information
        logCreationInformation(eventCreated);

        // Return the created event
        return eventCreated;
    }

    private void validateEvent(Event event) {
        eventValidator.validate(event);
    }

    private Optional<Event> createEvent(Event event) {
        return this.eventRepositoryPort.saveEvent(event);
    }

    private void logCreationInformation(Event event) {
        logger.info(EventMessageEnum.EVENT_CREATE_OK.getMessage());
        logger.info("Event created: " + event.toString());
    }
}
