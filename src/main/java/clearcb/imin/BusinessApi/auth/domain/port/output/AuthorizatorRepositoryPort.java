package clearcb.imin.BusinessApi.auth.domain.port.output;

import clearcb.imin.BusinessApi.auth.domain.model.Permission;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AuthorizatorRepositoryPort {

    Optional<Set<Permission>> getActionPermissionsByActionId(UUID actionId);

    Optional<Set<Permission>> getUserPermissionsByEventId(UUID userId, UUID eventId);

    Optional<Set<Permission>> getUserPermissionsByCommunityId(UUID userId, UUID communityId);
}
