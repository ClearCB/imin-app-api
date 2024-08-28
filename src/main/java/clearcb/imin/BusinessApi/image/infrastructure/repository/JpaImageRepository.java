package clearcb.imin.BusinessApi.image.infrastructure.repository;

import clearcb.imin.BusinessApi.image.infrastructure.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageEntity, UUID> {

    Set<ImageEntity> findByEventId(UUID eventId);
}
