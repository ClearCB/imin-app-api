package clearcb.imin.BusinessApi.auth.infrastructure.repository;

import clearcb.imin.BusinessApi.auth.infrastructure.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaMembershipRepository extends JpaRepository<MembershipEntity, UUID> {

    Optional<MembershipEntity> findByUserIdAndCommunityId(UUID userId, UUID communityId);
}
