package clearcb.imin.BusinessApi.common.domain.port.input;

import clearcb.imin.BusinessApi.common.domain.model.Tag;

import java.util.Set;

public interface GetAllTagsUseCase {

    Set<Tag> getAllTags();

}
