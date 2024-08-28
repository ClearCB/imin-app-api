package clearcb.imin.BusinessApi.event.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.event.domain.port.input.AddUserToEventUseCase;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;

import java.util.UUID;

public class AddUserToEventUseCaseImpl implements AddUserToEventUseCase {

    EventRepositoryPort eventRepositoryPort;
    Logger logger;


    public AddUserToEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, Logger logger) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.logger = logger;
    }

    @Override
    public boolean addUserToEvent(UUID eventId, UUID userId) {
        return eventRepositoryPort.addUserToEvent(eventId, userId);
    }
}
