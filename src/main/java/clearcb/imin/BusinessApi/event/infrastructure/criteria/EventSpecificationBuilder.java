package clearcb.imin.BusinessApi.event.infrastructure.criteria;

import clearcb.imin.BusinessApi.common.domain.criteria.SearchCriteria;
import clearcb.imin.BusinessApi.common.domain.criteria.SearchOperation;
import clearcb.imin.BusinessApi.event.infrastructure.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EventSpecificationBuilder {

    private final List<SearchCriteria> params;

    public EventSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final EventSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final EventSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<EventEntity> build() {
        if (params.isEmpty()) {
            return null;
        }

        Specification<EventEntity> result = new EventSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new EventSpecification(criteria))
                    : Specification.where(result).or(new EventSpecification(criteria));
        }

        return result;
    }

}
