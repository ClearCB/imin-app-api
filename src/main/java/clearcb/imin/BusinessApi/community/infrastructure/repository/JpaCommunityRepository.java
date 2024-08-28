package clearcb.imin.BusinessApi.community.infrastructure.repository;

import clearcb.imin.BusinessApi.community.infrastructure.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaCommunityRepository extends JpaRepository<CommunityEntity, UUID> {

    @Query("SELECT DISTINCT c FROM CommunityEntity c WHERE c.id = :communityId")
    Optional<CommunityEntity> findCommunityById(@Param("communityId") UUID communityId);

}
