package clearcb.imin.BusinessApi.event.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.port.input.GetAllEventUseCase;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;

import java.util.List;

public class GetAllEventUseCaseImpl implements GetAllEventUseCase {

    EventRepositoryPort eventRepositoryPort;
    Logger logger;

    public GetAllEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, Logger logger) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.logger = logger;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepositoryPort.getAllEvents();
    }
}

