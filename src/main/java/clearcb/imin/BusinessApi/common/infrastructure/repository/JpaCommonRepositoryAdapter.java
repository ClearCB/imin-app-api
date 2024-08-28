package clearcb.imin.BusinessApi.common.infrastructure.repository;

import clearcb.imin.BusinessApi.common.domain.model.Category;
import clearcb.imin.BusinessApi.common.domain.model.Tag;
import clearcb.imin.BusinessApi.common.domain.port.output.CategoryRepositoryPort;
import clearcb.imin.BusinessApi.common.domain.port.output.TagRepositoryPort;
import clearcb.imin.BusinessApi.common.infrastructure.mapper.CategoryMapper;
import clearcb.imin.BusinessApi.common.infrastructure.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaCommonRepositoryAdapter implements TagRepositoryPort, CategoryRepositoryPort {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaTagRepository jpaTagRepository;
    private final TagMapper tagMapper;
    private final CategoryMapper categoryMapper;


    @Override
    public Set<Category> getAllCategories() {
        return jpaCategoryRepository.findAll().stream().map(categoryMapper::toDomain).collect(Collectors.toSet());
    }

    @Override
    public Set<Tag> getAllTags() {
        return jpaTagRepository.findAll().stream().map(tagMapper::toDomain).collect(Collectors.toSet());
    }
}
