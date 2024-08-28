package clearcb.imin.BusinessApi.event.infrastructure.criteria;

import clearcb.imin.BusinessApi.common.domain.criteria.SearchCriteria;
import clearcb.imin.BusinessApi.common.domain.criteria.SearchOperation;
import clearcb.imin.BusinessApi.common.infrastructure.entity.CategoryEntity;
import clearcb.imin.BusinessApi.common.infrastructure.entity.TagEntity;
import clearcb.imin.BusinessApi.event.infrastructure.entity.EventEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EventSpecification implements Specification<EventEntity> {

    private final SearchCriteria searchCriteria;

    public EventSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<EventEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case CONTAINS:
                if (searchCriteria.getFilterKey().contains("content")) {
                    List<String> propertiesToCheck = Arrays.asList("title", "smallDescription", "largeDescription");

                    Predicate[] predicates = propertiesToCheck.stream()
                            .map(property -> cb.like(cb.lower(root.get(property)), "%" + strToSearch + "%"))
                            .toArray(Predicate[]::new);

                    return cb.or(predicates);
                } else {
                    return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
            case DOES_NOT_CONTAIN:
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case BEGINS_WITH:
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH:
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case ENDS_WITH:
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH:
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case EQUAL:
                if (searchCriteria.getFilterKey().contains("Date")) {
                    LocalDateTime date = LocalDateTime.parse(strToSearch);
                    return cb.equal(root.get(searchCriteria.getFilterKey()), date);
                } else {
                    return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }

            case NOT_EQUAL:
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NUL:
                return cb.isNull(root.get(searchCriteria.getFilterKey()));

            case NOT_NULL:
                return cb.isNotNull(root.get(searchCriteria.getFilterKey()));

            case GREATER_THAN:

                if (searchCriteria.getFilterKey().contains("Date")) {
                    LocalDateTime date = LocalDateTime.parse(strToSearch);
                    return cb.greaterThan(root.get(searchCriteria.getFilterKey()), date);
                } else {
                    return cb.greaterThan(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                }

            case GREATER_THAN_EQUAL:
                if (searchCriteria.getFilterKey().contains("Date")) {
                    LocalDateTime date = LocalDateTime.parse(strToSearch);
                    return cb.greaterThanOrEqualTo(root.get(searchCriteria.getFilterKey()), date);
                } else {
                    return cb.greaterThanOrEqualTo(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                }

            case LESS_THAN:
                if (searchCriteria.getFilterKey().contains("Date")) {
                    LocalDateTime date = LocalDateTime.parse(strToSearch);
                    return cb.lessThan(root.get(searchCriteria.getFilterKey()), date);
                } else {
                    return cb.lessThan(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                }

            case LESS_THAN_EQUAL:
                if (searchCriteria.getFilterKey().contains("Date")) {
                    LocalDateTime date = LocalDateTime.parse(strToSearch);
                    return cb.lessThanOrEqualTo(root.get(searchCriteria.getFilterKey()), date);
                } else {
                    return cb.lessThanOrEqualTo(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
                }

            case CONTAINS_TAG:
                if (searchCriteria.getFilterKey().equals("tags")) {
                    List<String> tags = (List<String>) searchCriteria.getValue();
                    Join<EventEntity, TagEntity> tagJoin = root.join("tags", JoinType.INNER);
                    return tagJoin.get("name").in(tags);
                }

            case CONTAINS_CATEGORY:
                if (searchCriteria.getFilterKey().equals("categories")) {
                    List<String> categories = (List<String>) searchCriteria.getValue();
                    Join<EventEntity, CategoryEntity> categoryJoin = root.join("categories", JoinType.INNER);
                    return categoryJoin.get("name").in(categories);
                }
        }
        return null;
    }

}