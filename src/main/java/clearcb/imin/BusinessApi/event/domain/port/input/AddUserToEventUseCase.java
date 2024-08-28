package clearcb.imin.BusinessApi.event.domain.port.input;

import java.util.UUID;

public interface AddUserToEventUseCase {

    boolean addUserToEvent(UUID eventId, UUID userId);
}
