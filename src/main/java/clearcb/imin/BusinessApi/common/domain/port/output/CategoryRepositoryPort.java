package clearcb.imin.BusinessApi.common.domain.port.output;

import clearcb.imin.BusinessApi.common.domain.model.Category;

import java.util.Set;

public interface CategoryRepositoryPort {

    Set<Category> getAllCategories();

}
