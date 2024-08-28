package clearcb.imin.BusinessApi.event.domain.port.input;

import clearcb.imin.BusinessApi.event.domain.model.Event;

import java.util.List;

public interface GetAllEventUseCase {
    List<Event> getAllEvents();
}
