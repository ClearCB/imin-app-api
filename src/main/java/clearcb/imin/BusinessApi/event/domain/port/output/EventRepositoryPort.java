package clearcb.imin.BusinessApi.event.domain.port.output;

import clearcb.imin.BusinessApi.common.domain.criteria.SearchCriteria;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepositoryPort {

    Optional<Event> getEventById(UUID eventId);

    List<Event> getAllEvents();

    void deleteEventById(UUID eventId);

    Optional<Event> saveEvent(Event event);

    Event updateEvent(Event event);

    List<Event> searchEvent(List<SearchCriteria> criteriaList, int pageSize, int pageNumber, String dataOption);

    boolean addUserToEvent(UUID eventId, UUID userId);

    boolean removeUserToEvent(UUID eventId, UUID userId);

    List<User> getEventAttendance(UUID eventId);

    List<Event> getUserAttendance(UUID userId);

    List<Event> getUsersEvents(UUID userId);


}
