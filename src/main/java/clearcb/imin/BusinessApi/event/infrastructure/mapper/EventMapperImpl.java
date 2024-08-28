package clearcb.imin.BusinessApi.event.infrastructure.mapper;

import clearcb.imin.BusinessApi.common.domain.model.Category;
import clearcb.imin.BusinessApi.common.domain.model.Tag;
import clearcb.imin.BusinessApi.common.infrastructure.dto.CategoryDTO;
import clearcb.imin.BusinessApi.common.infrastructure.dto.TagDTO;
import clearcb.imin.BusinessApi.common.infrastructure.entity.CategoryEntity;
import clearcb.imin.BusinessApi.common.infrastructure.entity.TagEntity;
import clearcb.imin.BusinessApi.common.infrastructure.mapper.CategoryMapper;
import clearcb.imin.BusinessApi.common.infrastructure.mapper.TagMapper;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.infrastructure.dto.EventDTO;
import clearcb.imin.BusinessApi.event.infrastructure.entity.EventEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EventMapperImpl implements EventMapper {

    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;

    public EventMapperImpl(CategoryMapper categoryMapper, TagMapper tagMapper) {
        this.categoryMapper = categoryMapper;
        this.tagMapper = tagMapper;
    }


    @Override
    public Event toDomain(EventDTO eventDTO) {

        Event event = new Event(
                UUID.fromString(eventDTO.getUserId()),
                eventDTO.getTitle(),
                eventDTO.getSmallDescription(),
                eventDTO.getLargeDescription(),
                eventDTO.getLocationName(),
                eventDTO.getLatitude(),
                eventDTO.getLongitude(),
                eventDTO.isOnline(),
                eventDTO.getStartDate(),
                eventDTO.getFinishDate()
        );

        Set<Category> categorySet = eventDTO.getCategories().stream().map(category -> {
            return new Category(category.id);
        }).collect(Collectors.toSet());
        Set<Tag> tagSet = eventDTO.getTags().stream().map(tag -> {
            return new Tag(tag.id, tag.name);
        }).collect(Collectors.toSet());
        event.setCategories(categorySet);
        event.setTags(tagSet);

        return event;
    }

    @Override
    public Event toDomainWithId(String id, EventDTO eventDTO) {

        Event event = new Event(
                UUID.fromString(id),
                UUID.fromString(eventDTO.getUserId()),
                eventDTO.getTitle(),
                eventDTO.getSmallDescription(),
                eventDTO.getLargeDescription(),
                eventDTO.getLocationName(),
                eventDTO.getLatitude(),
                eventDTO.getLongitude(),
                eventDTO.isOnline(),
                eventDTO.getStartDate(),
                eventDTO.getFinishDate()
        );

        Set<Category> categorySet = eventDTO.getCategories().stream().map(category -> {
            return categoryMapper.toDomainWithId(category.id, category);
        }).collect(Collectors.toSet());
        Set<Tag> tagSet = eventDTO.getTags().stream().map(tag -> {
            return tagMapper.toDomainWithId(tag.id, tag);
        }).collect(Collectors.toSet());

        event.setCategories(categorySet);
        event.setTags(tagSet);

        return event;
    }

    @Override
    public Event toDomain(EventEntity eventEntity) {

        Event event = new Event(
                eventEntity.getId(),
                eventEntity.getUserId(),
                eventEntity.getTitle(),
                eventEntity.getSmallDescription(),
                eventEntity.getLargeDescription(),
                eventEntity.getLocationName(),
                eventEntity.getLatitude(),
                eventEntity.getLongitude(),
                eventEntity.isOnline(),
                eventEntity.getStartDate(),
                eventEntity.getFinishDate()
        );

        Set<Category> categorySet = eventEntity.getCategories().stream().map(categoryMapper::toDomain).collect(Collectors.toSet());
        Set<Tag> tagSet = eventEntity.getTags().stream().map(tagMapper::toDomain).collect(Collectors.toSet());

        event.setCategories(categorySet);
        event.setTags(tagSet);

        return event;
    }

    @Override
    public EventDTO toDto(Event event) {

        EventDTO eventDTO = new EventDTO(
                event.getId().toString(),
                event.getUserId().toString(),
                event.getTitle(),
                event.getSmallDescription(),
                event.getLargeDescription(),
                event.getLocationName(),
                event.getLatitude(),
                event.getLongitude(),
                event.isOnline(),
                event.getStartDate(),
                event.getFinishDate()
        );

        Set<CategoryDTO> categorySet = event.getCategories().stream().map(categoryMapper::toDto).collect(Collectors.toSet());
        Set<TagDTO> tagSet = event.getTags().stream().map(tagMapper::toDto).collect(Collectors.toSet());

        eventDTO.setCategories(categorySet);
        eventDTO.setTags(tagSet);

        return eventDTO;
    }

    @Override
    public EventEntity toEntity(Event event) {

        EventEntity eventEntity = new EventEntity(
                event.getId(),
                event.getUserId(),
                event.getTitle(),
                event.getSmallDescription(),
                event.getLargeDescription(),
                event.getLocationName(),
                event.getLatitude(),
                event.getLongitude(),
                event.isOnline(),
                event.getStartDate(),
                event.getFinishDate()
        );

        Set<CategoryEntity> categorySet = event.getCategories().stream().map(categoryMapper::toEntity).collect(Collectors.toSet());
        Set<TagEntity> tagSet = event.getTags().stream().map(tagMapper::toEntity).collect(Collectors.toSet());

        eventEntity.setCategories(categorySet);
        eventEntity.setTags(tagSet);

        return eventEntity;
    }

    @Override
    public void setEventToUpdateValues(Event incomingEvent, EventEntity eventToUpdate) {
        eventToUpdate.setTitle(incomingEvent.getTitle());
        eventToUpdate.setSmallDescription(incomingEvent.getSmallDescription());
        eventToUpdate.setLargeDescription(incomingEvent.getLargeDescription());
        eventToUpdate.setLocationName(incomingEvent.getLocationName());
        eventToUpdate.setLatitude(incomingEvent.getLatitude());
        eventToUpdate.setLongitude(incomingEvent.getLongitude());
        eventToUpdate.setOnline(incomingEvent.isOnline());
        eventToUpdate.setStartDate(incomingEvent.getStartDate());
        eventToUpdate.setFinishDate(incomingEvent.getFinishDate());

        Set<CategoryEntity> categorySet = incomingEvent.getCategories().stream().map(categoryMapper::toEntity).collect(Collectors.toSet());
        Set<TagEntity> tagSet = incomingEvent.getTags().stream().map(tagMapper::toEntity).collect(Collectors.toSet());

        eventToUpdate.setCategories(categorySet);
        eventToUpdate.setTags(tagSet);

    }

}
