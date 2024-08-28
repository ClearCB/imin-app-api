package clearcb.imin.BusinessApi.event.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventNotFoundError;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.EventMessageEnum;
import clearcb.imin.BusinessApi.event.domain.port.input.GetEventUseCase;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public class GetEventUseCaseImpl implements GetEventUseCase {

    EventRepositoryPort eventRepositoryPort;
    Logger logger;

    public GetEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, Logger logger) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.logger = logger;
    }

    @Override
    public Event getEventById(UUID eventId) {
        Optional<Event> event = this.eventRepositoryPort.getEventById(eventId);

        if (event.isEmpty()) {
            throw new EventNotFoundError(EventMessageEnum.EVENT_NOT_FOUND.getMessage());
        }

        return event.get();
    }


}

