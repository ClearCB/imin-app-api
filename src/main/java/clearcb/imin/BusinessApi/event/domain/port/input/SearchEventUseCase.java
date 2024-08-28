package clearcb.imin.BusinessApi.event.domain.port.input;

import clearcb.imin.BusinessApi.common.domain.criteria.SearchCriteria;
import clearcb.imin.BusinessApi.event.domain.model.Event;

import java.util.List;

public interface SearchEventUseCase {

    List<Event> searchEvents(List<SearchCriteria> criteriaList, int pageSize, int pageNumber, String dataOption);
}
