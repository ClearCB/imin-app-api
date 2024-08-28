package clearcb.imin.BusinessApi.event.infrastructure.criteria;

import clearcb.imin.BusinessApi.common.domain.criteria.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSearchDTO {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

}