package clearcb.imin.BusinessApi.event.domain.port.input;

import clearcb.imin.BusinessApi.event.domain.model.Event;

public interface CreateEventUseCase {

    Event create(Event event);
}
