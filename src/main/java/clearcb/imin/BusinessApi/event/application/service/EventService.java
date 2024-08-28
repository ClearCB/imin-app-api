package clearcb.imin.BusinessApi.event.application.service;

import clearcb.imin.BusinessApi.common.domain.criteria.SearchCriteria;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventNotFoundError;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventValidationError;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.User;
import clearcb.imin.BusinessApi.event.domain.port.input.*;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;

import java.util.List;
import java.util.UUID;

public class EventService implements
        GetEventUseCase,
        GetAllEventUseCase,
        CreateEventUseCase,
        UpdateEventUseCase,
        DeleteEventUseCase,
        SearchEventUseCase,
        AddUserToEventUseCase {

    private final GetEventUseCase getEventUseCase;
    private final CreateEventUseCase createEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final GetAllEventUseCase getAllEventUseCase;
    private final SearchEventUseCase searchEventUseCase;
    private final AddUserToEventUseCase addUserToEventUseCase;

    private final EventRepositoryPort eventRepositoryPort;


    public EventService(GetEventUseCase getEventUseCase,
                        UpdateEventUseCase updateEventUseCase,
                        DeleteEventUseCase deleteEventUseCase,
                        CreateEventUseCase createEventUseCase,
                        GetAllEventUseCase getAllEventUseCase,
                        SearchEventUseCase searchEventUseCase,
                        AddUserToEventUseCase addUserToEventUseCase,
                        EventRepositoryPort eventRepositoryPort) {
        this.getEventUseCase = getEventUseCase;
        this.updateEventUseCase = updateEventUseCase;
        this.deleteEventUseCase = deleteEventUseCase;
        this.createEventUseCase = createEventUseCase;
        this.getAllEventUseCase = getAllEventUseCase;
        this.searchEventUseCase = searchEventUseCase;
        this.addUserToEventUseCase = addUserToEventUseCase;
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Event create(Event event) throws EventValidationError {
        return createEventUseCase.create(event);
    }

    @Override
    public void deleteEventById(UUID eventId) throws EventNotFoundError {
        this.deleteEventUseCase.deleteEventById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return getAllEventUseCase.getAllEvents();
    }

    @Override
    public Event getEventById(UUID eventId) throws EventNotFoundError {
        return getEventUseCase.getEventById(eventId);
    }

    @Override
    public Event update(Event event) throws EventValidationError, EventNotFoundError {
        return updateEventUseCase.update(event);
    }

    @Override
    public List<Event> searchEvents(List<SearchCriteria> criteriaList, int pageSize, int pageNumber, String dataOption) {
        return searchEventUseCase.searchEvents(criteriaList, pageSize, pageNumber, dataOption);
    }

    @Override
    public boolean addUserToEvent(UUID eventId, UUID userId) {
        return addUserToEventUseCase.addUserToEvent(eventId, userId);
    }

    public boolean removeUserToEvent(UUID eventId, UUID userId) {
        return eventRepositoryPort.removeUserToEvent(eventId, userId);
    }

    public List<Event> getUserAttendance(UUID userId) {
        return eventRepositoryPort.getUserAttendance(userId);
    }

    public List<Event> getUsersEvents(UUID eventId) {
        return eventRepositoryPort.getUsersEvents(eventId);
    }

    public List<User> getEventAttendance(UUID eventId) {
        return eventRepositoryPort.getEventAttendance(eventId);
    }

}
