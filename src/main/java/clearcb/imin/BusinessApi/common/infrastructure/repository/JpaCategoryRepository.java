package clearcb.imin.BusinessApi.common.infrastructure.repository;

import clearcb.imin.BusinessApi.common.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}