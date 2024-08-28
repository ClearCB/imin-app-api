package clearcb.imin.BusinessApi.event.infrastructure.mapper;

import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.infrastructure.dto.EventDTO;
import clearcb.imin.BusinessApi.event.infrastructure.entity.EventEntity;

public interface EventMapper {

    Event toDomain(EventDTO eventDTO);

    Event toDomainWithId(String id, EventDTO eventDTO);

    Event toDomain(EventEntity eventEntity);

    EventDTO toDto(Event event);

    EventEntity toEntity(Event event);

    void setEventToUpdateValues(Event incomingEvent, EventEntity eventToUpdate);

}
