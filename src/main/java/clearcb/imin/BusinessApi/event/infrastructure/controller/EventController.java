package clearcb.imin.BusinessApi.event.infrastructure.controller;

import clearcb.imin.BusinessApi.auth.infrastructure.entity.UserEntity;
import clearcb.imin.BusinessApi.auth.infrastructure.repository.JpaUserRepository;
import clearcb.imin.BusinessApi.common.domain.criteria.ApiResponse;
import clearcb.imin.BusinessApi.common.infrastructure.validation.UUIDConstraint;
import clearcb.imin.BusinessApi.event.application.service.EventService;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.model.User;
import clearcb.imin.BusinessApi.event.infrastructure.criteria.EventSearchDTO;
import clearcb.imin.BusinessApi.event.infrastructure.dto.AddUserToEventRequestDTO;
import clearcb.imin.BusinessApi.event.infrastructure.dto.EventDTO;
import clearcb.imin.BusinessApi.event.infrastructure.mapper.EventMapper;
import clearcb.imin.BusinessApi.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;
    private final JwtService jwtService;
    private final JpaUserRepository jpaUserRepository;
    private final EventMapper eventMapper;

    public EventController(
            EventMapper eventMapper,
            EventService eventService,
            JwtService jwtService,
            JpaUserRepository jpaUserRepository
    ) {
        this.eventMapper = eventMapper;
        this.eventService = eventService;
        this.jwtService = jwtService;
        this.jpaUserRepository = jpaUserRepository;
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createEvent(@Valid @RequestBody EventDTO eventDTO, HttpServletRequest request) {

        UUID userId = this.getUserId(request);
        assert userId != null;
        eventDTO.setUserId(userId.toString());
        Event event = eventMapper.toDomain(eventDTO);

        event.setUserId(userId);

        // Create event
        Event createdEvent = eventService.create(event);

        // If not present, error otherwise Ok.
        if (createdEvent != null) {

            EventDTO createdEventDTO = eventMapper.toDto(createdEvent);

            ApiResponse apiResponse = new ApiResponse(
                    createdEventDTO,
                    "",
                    HttpStatus.CREATED.value()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PutMapping(path = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateEvent(HttpServletRequest request, @Valid @RequestBody EventDTO eventDTO, @PathVariable("id") @UUIDConstraint String id) {

        UUID userId = this.getUserId(request);
        Event event = eventService.getEventById(UUID.fromString(id));

        assert userId != null;
        if (!Objects.equals(event.getUserId().toString(), userId.toString())){
            ApiResponse apiResponse = new ApiResponse(
                    null,
                    "Cant update",
                    HttpStatus.FORBIDDEN.value()
            );
            return ResponseEntity.ok(apiResponse);
        }

        eventDTO.setUserId(userId.toString());
        // Update event
        Event updatedEvent = eventService.update(eventMapper.toDomainWithId(id, eventDTO));

        // If updated ok otherwise error
        if (updatedEvent != null) {

            EventDTO updatedEventDTO = eventMapper.toDto(updatedEvent);

            ApiResponse apiResponse = new ApiResponse(
                    updatedEventDTO,
                    "",
                    HttpStatus.OK.value()
            );
            return ResponseEntity.ok(apiResponse);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiResponse> deleteEvent(@PathVariable("id") @UUIDConstraint String id) {

        eventService.deleteEventById(UUID.fromString(id));

        ApiResponse apiResponse = new ApiResponse(
                null,
                "Item deleted",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getEvent(@PathVariable("id") @UUIDConstraint String id) {

        Event event = eventService.getEventById(UUID.fromString(id));
        EventDTO eventDTO = eventMapper.toDto(event);

        ApiResponse apiResponse = new ApiResponse(
                eventDTO,
                "",
                HttpStatus.FOUND.value()
        );
        return ResponseEntity.ok(apiResponse);

    }


    @GetMapping(path = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAllEvent() {

        List<Event> events = eventService.getAllEvents();
        List<EventDTO> eventsDto = events.stream().map(eventMapper::toDto).toList();

        ApiResponse apiResponse = new ApiResponse(
                eventsDto,
                "",
                HttpStatus.FOUND.value()
        );
        return ResponseEntity.ok(apiResponse);

    }


    @PostMapping(path = "/search",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> searchEvent(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                   @RequestBody EventSearchDTO eventSearchDTO) {

        List<Event> events = this.eventService.searchEvents(
                eventSearchDTO.getSearchCriteriaList(), pageSize, pageNum, eventSearchDTO.getDataOption());
        List<EventDTO> eventDTOS = events.stream().map(eventMapper::toDto).toList();

        ApiResponse apiResponse = new ApiResponse(
                eventDTOS,
                "",
                HttpStatus.FOUND.value()
        );
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping(path = "/add-user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addUserToEvent(@RequestBody AddUserToEventRequestDTO addUserToEventRequestDTO) {

        boolean addedUserToEvent = eventService.addUserToEvent(UUID.fromString(addUserToEventRequestDTO.eventId()), UUID.fromString(addUserToEventRequestDTO.userId()));

        ApiResponse apiResponse = new ApiResponse(
                addedUserToEvent,
                "",
                HttpStatus.FOUND.value()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "/remove-user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> removeUserFromEvent(@RequestBody AddUserToEventRequestDTO addUserToEventRequestDTO) {

        boolean addedUserToEvent = eventService.removeUserToEvent(UUID.fromString(addUserToEventRequestDTO.eventId()), UUID.fromString(addUserToEventRequestDTO.userId()));

        ApiResponse apiResponse = new ApiResponse(
                addedUserToEvent,
                "",
                HttpStatus.FOUND.value()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "user/event/attendance/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getUserAttendance(@PathVariable("id") @UUIDConstraint String id) {

        List<EventDTO> eventDTOS = eventService.getUserAttendance(UUID.fromString(id)).stream().map(eventMapper::toDto).toList();

        ApiResponse apiResponse = new ApiResponse(
                eventDTOS,
                "",
                HttpStatus.FOUND.value()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "user/event/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getUsersEvents(@PathVariable("id") @UUIDConstraint String id) {

        List<EventDTO> eventDTOS = eventService.getUsersEvents(UUID.fromString(id)).stream().map(eventMapper::toDto).toList();

        ApiResponse apiResponse = new ApiResponse(
                eventDTOS,
                "",
                HttpStatus.FOUND.value()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "/event/user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getEventAttendance(@PathVariable("id") @UUIDConstraint String id) {

        List<User> users = eventService.getEventAttendance(UUID.fromString(id));

        ApiResponse apiResponse = new ApiResponse(
                users,
                "",
                HttpStatus.FOUND.value()
        );
        return ResponseEntity.ok(apiResponse);
    }


    private UUID getUserId(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            String token = authorizationHeader.substring(7); // Extract the token value
            // Now you have the token, you can do whatever you need with it
            String username = jwtService.getUsernameFromToken(token);
            Optional<UserEntity> userEntityOptional = jpaUserRepository.findByUsername(username);

            return userEntityOptional.get().getId();
        }

        return null;

    }
}
