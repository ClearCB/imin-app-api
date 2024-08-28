package clearcb.imin.BusinessApi.config;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.event.application.service.EventService;
import clearcb.imin.BusinessApi.event.application.usecase.*;
import clearcb.imin.BusinessApi.event.domain.port.input.*;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;
import clearcb.imin.BusinessApi.event.domain.validator.EventValidator;
import clearcb.imin.BusinessApi.event.domain.validator.EventValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanEventConfiguration {

    @Bean
    public CreateEventUseCase createEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, Logger logger, EventValidator eventValidator) {
        return new CreateEventUseCaseImpl(eventRepositoryPort, logger, eventValidator);
    }


    @Bean
    public UpdateEventUseCase updateEventUseCase(EventRepositoryPort eventRepositoryPort, Logger logger, EventValidator eventValidator) {
        return new UpdateEventUseCaseImpl(eventRepositoryPort, logger, eventValidator);
    }

    @Bean
    public GetAllEventUseCase getAllEventUseCase(EventRepositoryPort eventRepositoryPort, Logger logger, EventValidator eventValidator) {
        return new GetAllEventUseCaseImpl(eventRepositoryPort, logger);
    }


    @Bean
    public DeleteEventUseCase deleteEventUseCase(EventRepositoryPort eventRepositoryPort, Logger logger) {
        return new DeleteEventUseCaseImpl(eventRepositoryPort, logger);
    }


    @Bean
    public GetEventUseCase getEventUseCase(EventRepositoryPort eventRepositoryPort, Logger logger) {
        return new GetEventUseCaseImpl(eventRepositoryPort, logger);
    }

    @Bean
    public SearchEventUseCase searchEventUseCase(EventRepositoryPort eventRepositoryPort, Logger logger) {
        return new SearchEventUseCaseImpl(eventRepositoryPort, logger);
    }

    @Bean
    public AddUserToEventUseCase addUserToEventUseCase(EventRepositoryPort eventRepositoryPort, Logger logger) {
        return new AddUserToEventUseCaseImpl(eventRepositoryPort, logger);
    }

    @Bean
    public EventService eventService(GetEventUseCase getEventUseCase,
                                     UpdateEventUseCase updateEventUseCase,
                                     DeleteEventUseCase deleteEventUseCase,
                                     CreateEventUseCase createEventUseCase,
                                     GetAllEventUseCase getAllEventUseCase,
                                     SearchEventUseCase searchEventUseCase,
                                     AddUserToEventUseCase addUserToEventUseCase,
                                     EventRepositoryPort eventRepositoryPort) {
        return new EventService(getEventUseCase, updateEventUseCase,
                deleteEventUseCase, createEventUseCase, getAllEventUseCase, searchEventUseCase, addUserToEventUseCase, eventRepositoryPort);
    }

    @Bean
    public EventValidator eventValidator() {
        return new EventValidatorImpl();
    }

}
