package clearcb.imin.BusinessApi.event.domain.port.input;

import java.util.UUID;

public interface DeleteEventUseCase {

    void deleteEventById(UUID eventId);
}
