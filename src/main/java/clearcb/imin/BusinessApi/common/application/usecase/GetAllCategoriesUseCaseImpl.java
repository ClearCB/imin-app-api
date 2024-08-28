package clearcb.imin.BusinessApi.common.application.usecase;

import clearcb.imin.BusinessApi.common.domain.model.Category;
import clearcb.imin.BusinessApi.common.domain.port.input.GetAllCategoriesUseCase;
import clearcb.imin.BusinessApi.common.domain.port.output.CategoryRepositoryPort;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;

import java.util.Set;

public class GetAllCategoriesUseCaseImpl implements GetAllCategoriesUseCase {

    CategoryRepositoryPort categoryRepositoryPort;
    Logger logger;


    public GetAllCategoriesUseCaseImpl(CategoryRepositoryPort categoryRepositoryPort, Logger logger) {
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.logger = logger;
    }

    @Override
    public Set<Category> getAllCategories() {
        return categoryRepositoryPort.getAllCategories();
    }
}
