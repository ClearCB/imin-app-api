package clearcb.imin.BusinessApi.event.application.usecase;

import clearcb.imin.BusinessApi.common.domain.criteria.SearchCriteria;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.event.domain.model.Event;
import clearcb.imin.BusinessApi.event.domain.port.input.SearchEventUseCase;
import clearcb.imin.BusinessApi.event.domain.port.output.EventRepositoryPort;

import java.util.List;

public class SearchEventUseCaseImpl implements SearchEventUseCase {

    EventRepositoryPort eventRepositoryPort;
    Logger logger;

    public SearchEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, Logger logger) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.logger = logger;
    }


    @Override
    public List<Event> searchEvents(List<SearchCriteria> criteriaList, int pageSize, int pageNumber, String dataOption) {
        return eventRepositoryPort.searchEvent(criteriaList, pageSize, pageNumber, dataOption);
    }
}

