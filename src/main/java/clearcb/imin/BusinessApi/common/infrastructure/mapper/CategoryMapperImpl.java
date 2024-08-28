package clearcb.imin.BusinessApi.common.infrastructure.mapper;

import clearcb.imin.BusinessApi.common.domain.model.Category;
import clearcb.imin.BusinessApi.common.infrastructure.dto.CategoryDTO;
import clearcb.imin.BusinessApi.common.infrastructure.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {


    @Override
    public Category toDomain(CategoryDTO categoryDTO) {

        return new Category(
                categoryDTO.getName(),
                categoryDTO.getIcon()
        );
    }

    @Override
    public Category toDomainWithId(Long id, CategoryDTO categoryDTO) {
        return new Category(
                id,
                categoryDTO.getName(),
                categoryDTO.getIcon()
        );
    }

    @Override
    public Category toDomain(CategoryEntity categoryEntity) {
        return new Category(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getIcon()
        );
    }

    @Override
    public CategoryDTO toDto(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getIcon()
        );
    }

    @Override
    public CategoryEntity toEntity(Category category) {
        return new CategoryEntity(
                category.getId(),
                category.getName(),
                category.getIcon()
        );
    }

}
