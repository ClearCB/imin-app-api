package clearcb.imin.BusinessApi.event.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventNotFoundError;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.EventMessageEnum;
import clearcb.imin.BusinessApi.event.domain.port.input.DeleteEventUseCase;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    EventRepositoryPort eventRepositoryPort;
    Logger logger;

    public DeleteEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, Logger logger) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.logger = logger;
    }

    @Override
    public void deleteEventById(UUID eventId) {

        // Check if the event exist
        ensureEventExist(eventId);

        // Delete de event
        deleteEvent(eventId);

        // Log information related to the deletion
        logDeleteInformation(eventId);
    }

    private void deleteEvent(UUID eventId) {
        this.eventRepositoryPort.deleteEventById(eventId);
    }

    private void logDeleteInformation(UUID eventId) {
        logger.info(EventMessageEnum.EVENT_DELETE_OK.getMessage());
        logger.info("Deleted event with id: " + eventId);
    }

    private void ensureEventExist(UUID eventId) {
        Optional<Event> eventOptional = this.eventRepositoryPort.getEventById(eventId);

        if (eventOptional.isEmpty()) {
            throw new EventNotFoundError(EventMessageEnum.EVENT_NOT_FOUND.getMessage());
        }
    }
}
