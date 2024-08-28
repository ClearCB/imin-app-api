package clearcb.imin.BusinessApi.common.infrastructure.mapper;


import clearcb.imin.BusinessApi.common.domain.model.Category;
import clearcb.imin.BusinessApi.common.infrastructure.dto.CategoryDTO;
import clearcb.imin.BusinessApi.common.infrastructure.entity.CategoryEntity;

public interface CategoryMapper {

    Category toDomain(CategoryDTO categoryDTO);

    Category toDomainWithId(Long id, CategoryDTO categoryDTO);

    Category toDomain(CategoryEntity categoryEntity);

    CategoryDTO toDto(Category category);

    CategoryEntity toEntity(Category category);

}
