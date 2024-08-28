package clearcb.imin.BusinessApi.common.domain.port.input;

import clearcb.imin.BusinessApi.common.domain.model.Category;

import java.util.Set;

public interface GetAllCategoriesUseCase {

    Set<Category> getAllCategories();

}
