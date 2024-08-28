package clearcb.imin.BusinessApi.event.domain.port.input;

import clearcb.imin.BusinessApi.event.domain.model.Event;

import java.util.UUID;

public interface GetEventUseCase {

    Event getEventById(UUID eventId);
}
