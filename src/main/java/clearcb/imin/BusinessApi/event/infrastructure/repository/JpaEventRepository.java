package clearcb.imin.BusinessApi.event.infrastructure.repository;

import clearcb.imin.BusinessApi.event.infrastructure.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaEventRepository extends JpaRepository<EventEntity, UUID>, JpaSpecificationExecutor<EventEntity> {
}
