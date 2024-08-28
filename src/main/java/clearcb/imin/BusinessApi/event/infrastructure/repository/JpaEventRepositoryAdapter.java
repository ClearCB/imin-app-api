package clearcb.imin.BusinessApi.event.infrastructure.repository;

import clearcb.imin.BusinessApi.auth.infrastructure.entity.AttendanceEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.RoleEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.entity.UserEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.repository.JpaAttendenceRepository;
import clearcb.imin.BusinessApi.auth.infrastructure.repository.JpaRoleRepository;
import clearcb.imin.BusinessApi.auth.infrastructure.repository.JpaUserRepository;
import clearcb.imin.BusinessApi.common.domain.criteria.SearchCriteria;
import clearcb.imin.BusinessApi.common.domain.exception.DatabaseError;
import clearcb.imin.BusinessApi.common.domain.model.Category;
import clearcb.imin.BusinessApi.common.domain.model.Tag;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.common.infrastructure.entity.CategoryEntity;
import clearcb.imin.BusinessApi.common.infrastructure.entity.TagEntity;
import clearcb.imin.BusinessApi.common.infrastructure.mapper.CategoryMapper;
import clearcb.imin.BusinessApi.common.infrastructure.mapper.TagMapper;
import clearcb.imin.BusinessApi.event.domain.exceptions.EventNotFoundError;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.EventMessageEnum;
import clearcb.imin.BusinessApi.event.domain.model.User;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;
import clearcb.imin.BusinessApi.event.infrastructure.criteria.EventSpecificationBuilder;
import clearcb.imin.BusinessApi.event.infrastructure.entity.EventEntity;
import clearcb.imin.BusinessApi.event.infrastructure.mapper.EventMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaEventRepositoryAdapter implements EventRepositoryPort {

    private final JpaEventRepository jpaEventRepository;
    private final JpaAttendenceRepository jpaAttendanceRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaUserRepository jpaUserRepository;
    private final EventMapper eventMapper;
    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;
    private final Logger logger;
    private final EntityManager entityManager;

    @Override
    public Optional<Event> getEventById(UUID eventId) {

        try {
            return jpaEventRepository.findById(eventId).map(eventMapper::toDomain);
        } catch (Exception e) {
            logger.error("Error getting event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public List<Event> getAllEvents() {

        try {
            return jpaEventRepository.findAll().stream().map(eventMapper::toDomain).toList();
        } catch (Exception e) {
            logger.error("Error getting all events: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public void deleteEventById(UUID eventId) {

        try {
            jpaEventRepository.deleteById(eventId);
        } catch (Exception e) {
            logger.error("Error deleting event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    @Transactional
    public Optional<Event> saveEvent(Event event) {

        try {

            EventEntity eventEntityInserted = jpaEventRepository.save(eventMapper.toEntity(event));

            entityManager.flush();
            entityManager.clear();

            return jpaEventRepository
                    .findById(eventEntityInserted.getId()).map(eventMapper::toDomain);

        } catch (Exception e) {
            logger.error("Error saving event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public Event updateEvent(Event incomingEvent) {
        try {

            // Retrieve event if present
            EventEntity eventEntityToUpdate = getEvent(incomingEvent.getId());

            // Assign incoming values to existing entity
            eventMapper.setEventToUpdateValues(incomingEvent, eventEntityToUpdate);

            Set<Category> categoryList = incomingEvent.getCategories();
            Set<CategoryEntity> categoryEntitySet = categoryList.stream().map(categoryMapper::toEntity).collect(Collectors.toSet());

            eventEntityToUpdate.setCategories(categoryEntitySet);

            Set<Tag> tagList = incomingEvent.getTags();
            Set<TagEntity> tagEntitySet = tagList.stream().map(tagMapper::toEntity).collect(Collectors.toSet());

            eventEntityToUpdate.setTags(tagEntitySet);

            // Save new values
            EventEntity updatedEventEntity = jpaEventRepository.save(eventEntityToUpdate);

            // Return updated entity mapped to domain model
            return eventMapper.toDomain(updatedEventEntity);
        } catch (Exception e) {
            logger.error("Error updating event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }
    }

    @Override
    public List<Event> searchEvent(List<SearchCriteria> criteriaList, int pageSize, int pageNumber, String dataOption) {

        try {

            EventSpecificationBuilder builder = new EventSpecificationBuilder();

            if (criteriaList != null) {
                criteriaList.forEach(x -> {
                    x.setDataOption(dataOption);
                    builder.with(x);
                });

            }

            Pageable page = PageRequest.of(pageNumber, pageSize,
                    Sort.by("title").ascending().and(
                            Sort.by("smallDescription")).ascending());

            Page<EventEntity> eventEntities = jpaEventRepository.findAll(builder.build(), page);

            return eventEntities.toList().stream().map(eventMapper::toDomain).toList();
        } catch (Exception e) {
            logger.error("Error updating event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public boolean addUserToEvent(UUID eventId, UUID userId) {

        try {

            Optional<RoleEntity> roleEntityOptional = jpaRoleRepository.findById(UUID.fromString("890e8756-f624-46a1-9f05-0c8f73cb2122"));
            Optional<UserEntity> userEntityOptional = jpaUserRepository.findById(userId);
            Optional<EventEntity> eventEntityOptional = jpaEventRepository.findById(eventId);

            if (roleEntityOptional.isEmpty() || userEntityOptional.isEmpty() || eventEntityOptional.isEmpty()) {
                return false;
            }

            Optional<AttendanceEntity> attendanceEntity = jpaAttendanceRepository.findByEventIdAndUserId(eventId, userId);

            if (attendanceEntity.isPresent()) {
                return false;
            }

            List<AttendanceEntity> eventUserList = jpaAttendanceRepository.findByEventId(eventId);
            EventEntity eventEntity = eventEntityOptional.get();

            if (eventUserList.size() == eventEntity.getMaxAttendance()) {
                return false;
            }

            AttendanceEntity newAttendance = new AttendanceEntity(userEntityOptional.get(), eventEntityOptional.get(), roleEntityOptional.get());
            jpaAttendanceRepository.save(newAttendance);

            return true;

        } catch (Exception e) {
            logger.error("Error updating event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public boolean removeUserToEvent(UUID eventId, UUID userId) {

        try {

            Optional<AttendanceEntity> attendanceEntity = jpaAttendanceRepository.findByEventIdAndUserId(eventId, userId);

            if (attendanceEntity.isEmpty()) {
                return true;
            }

            jpaAttendanceRepository.delete(attendanceEntity.get());
            return true;

        } catch (Exception e) {
            logger.error("Error updating event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public List<Event> getUserAttendance(UUID userId) {

        try {

            List<AttendanceEntity> attendanceEntities = jpaAttendanceRepository.findByUserId(userId);
            List<EventEntity> eventEntities = attendanceEntities.stream().map(AttendanceEntity::getEvent).toList();

            return eventEntities.stream().map(eventMapper::toDomain).toList();

        } catch (Exception e) {
            logger.error("Error updating event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public List<Event> getUsersEvents(UUID userId) {


        try {

            Optional<UserEntity> userEntityOptional = jpaUserRepository.findById(userId);

            if (userEntityOptional.isPresent()) {
                List<EventEntity> eventEntities = userEntityOptional.get().getEvents().stream().toList();
                return eventEntities.stream().map(eventMapper::toDomain).toList();
            }

            return Collections.emptyList();

        } catch (Exception e) {
            logger.error("Error updating event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public List<User> getEventAttendance(UUID eventId) {

        try {

            List<AttendanceEntity> attendanceEntities = jpaAttendanceRepository.findByEventId(eventId);
            return attendanceEntities.stream().map(at -> {
                return new User(at.getUser().getId(), at.getUser().getUsername(), at.getRole().getName(), at.getUser().getEmail());
            }).toList();

        } catch (Exception e) {
            logger.error("Error updating event: " + e.getMessage());
            throw new DatabaseError(EventMessageEnum.EVENT_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    private EventEntity getEvent(UUID id) {
        return jpaEventRepository
                .findById(id).orElseThrow(() -> new EventNotFoundError(EventMessageEnum.EVENT_NOT_FOUND.getMessage()));
    }
}
