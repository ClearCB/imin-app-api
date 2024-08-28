package clearcb.imin.BusinessApi.config;

import clearcb.imin.BusinessApi.common.application.usecase.GetAllCategoriesUseCaseImpl;
import clearcb.imin.BusinessApi.common.application.usecase.GetAllTagsUseCaseImpl;
import clearcb.imin.BusinessApi.common.domain.port.input.GetAllCategoriesUseCase;
import clearcb.imin.BusinessApi.common.domain.port.input.GetAllTagsUseCase;
import clearcb.imin.BusinessApi.common.domain.port.output.CategoryRepositoryPort;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.common.domain.port.output.TagRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCommonConfiguration {

    @Bean
    public GetAllCategoriesUseCase getAllCategoriesUseCase(CategoryRepositoryPort categoryRepositoryPort, Logger logger) {
        return new GetAllCategoriesUseCaseImpl(categoryRepositoryPort, logger);
    }


    @Bean
    public GetAllTagsUseCase getAllTagsUseCase(TagRepositoryPort tagRepositoryPort, Logger logger) {
        return new GetAllTagsUseCaseImpl(tagRepositoryPort, logger);
    }
}
