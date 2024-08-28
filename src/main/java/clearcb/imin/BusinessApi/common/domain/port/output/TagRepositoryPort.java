package clearcb.imin.BusinessApi.common.domain.port.output;

import clearcb.imin.BusinessApi.common.domain.model.Tag;

import java.util.Set;

public interface TagRepositoryPort {

    Set<Tag> getAllTags();

}
