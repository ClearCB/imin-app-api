package clearcb.imin.BusinessApi.auth.application.usecase;

import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationRequest;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationResponse;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizatorMessageEnum;
import clearcb.imin.BusinessApi.auth.domain.model.Permission;
import clearcb.imin.BusinessApi.auth.domain.port.input.AuthorizeUseCase;
import clearcb.imin.BusinessApi.auth.domain.port.output.AuthorizatorRepositoryPort;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;

import java.util.Optional;
import java.util.Set;

public class CommunityAuthorizeUseCaseImpl implements AuthorizeUseCase {

    private final AuthorizatorRepositoryPort authorizatorRepositoryPort;
    private final Logger logger;

    public CommunityAuthorizeUseCaseImpl(AuthorizatorRepositoryPort authorizatorRepositoryPort,
                                         Logger logger) {
        this.authorizatorRepositoryPort = authorizatorRepositoryPort;
        this.logger = logger;
    }

    @Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) {

        // Get the action permissions
        Optional<Set<Permission>> actionPermissionsOptional = authorizatorRepositoryPort.getActionPermissionsByActionId(authorizationRequest.actionId());

//        ensureActionExist(actionPermissionsOptional);

        // If action not found, unauthorized
        if (actionPermissionsOptional.isEmpty()) {
            logger.info("Unauthorized reason: " + AuthorizatorMessageEnum.ACTION_NOT_FOUND.getMessage());
            return new AuthorizationResponse(false, AuthorizatorMessageEnum.ACTION_NOT_FOUND.getMessage());
        }

        // Get the user's permissions for the community
        Optional<Set<Permission>> usersPermissionsByCommunity = authorizatorRepositoryPort.getUserPermissionsByCommunityId(
                authorizationRequest.actorId(), authorizationRequest.objectId());

//        ensureUserAndCommunityExist(actionPermissionsOptional);

        // If user is not present in the community, unauthorized
        if (usersPermissionsByCommunity.isEmpty()) {
            logger.info("Unauthorized reason: " + AuthorizatorMessageEnum.ACTOR_NOT_FOUND.getMessage());
            return new AuthorizationResponse(false, AuthorizatorMessageEnum.ACTOR_NOT_FOUND.getMessage());
        }

        Set<Permission> actionPermissionsNeeded = actionPermissionsOptional.get();

        // If action does not require permissions, authorized
        if (actionPermissionsNeeded.isEmpty()) {
            logger.info("Authorized reason: action no need permission");
            return new AuthorizationResponse(true, "");
        }

        // Check if the user has all permissions needed for the action
        boolean authorized = usersPermissionsByCommunity.get().containsAll(actionPermissionsNeeded);

        // If not authorized, add an authorization message
        if (!authorized) {
            logger.info("Unauthorized reason: " + AuthorizatorMessageEnum.ACTOR_HAS_NOT_PERMISSION_NEED.getMessage());
            return new AuthorizationResponse(false, AuthorizatorMessageEnum.ACTOR_HAS_NOT_PERMISSION_NEED.getMessage());
        }

        return new AuthorizationResponse(true, "");
    }
}