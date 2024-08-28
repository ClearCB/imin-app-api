package clearcb.imin.BusinessApi.auth.infrastructure.repository;

import clearcb.imin.BusinessApi.auth.infrastructure.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaActionRepository extends JpaRepository<ActionEntity, UUID> {

}
