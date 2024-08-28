package clearcb.imin.BusinessApi.common.infrastructure.repository;

import clearcb.imin.BusinessApi.common.infrastructure.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTagRepository extends JpaRepository<TagEntity, Long> {
}
