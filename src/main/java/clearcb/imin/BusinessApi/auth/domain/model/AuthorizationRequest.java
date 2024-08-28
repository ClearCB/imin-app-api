package clearcb.imin.BusinessApi.auth.domain.model;

import java.util.UUID;

public record AuthorizationRequest(UUID actorId, UUID objectId, UUID actionId) {

}