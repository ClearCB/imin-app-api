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

public class EventAuthorizeUseCaseImpl implements AuthorizeUseCase {

    private final AuthorizatorRepositoryPort authorizatorRepositoryPort;
    private final Logger logger;

    public EventAuthorizeUseCaseImpl(AuthorizatorRepositoryPort authorizatorRepositoryPort, Logger logger) {
        this.authorizatorRepositoryPort = authorizatorRepositoryPort;
        this.logger = logger;
    }

    @Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) {

        // Get the action permissions
        Optional<Set<Permission>> actionPermissionsOptional = authorizatorRepositoryPort.getActionPermissionsByActionId(authorizationRequest.actionId());

        // If action not found, unauthorized
        if (actionPermissionsOptional.isEmpty()) {
            logger.info("Unauthorized reason: " + AuthorizatorMessageEnum.ACTION_NOT_FOUND.getMessage());
            return new AuthorizationResponse(false, AuthorizatorMessageEnum.ACTION_NOT_FOUND.getMessage());
        }

        //        ensureCommunityExist(actionPermissionsOptional);

        // Get the users permissions to the event
        Optional<Set<Permission>> usersPermissionsByEvent = authorizatorRepositoryPort.getUserPermissionsByEventId(
                authorizationRequest.actorId(), authorizationRequest.objectId());

        // If user is not present to the event, unauthorized
        if (usersPermissionsByEvent.isEmpty()) {
            logger.info("Unauthorized reason: " + AuthorizatorMessageEnum.ACTOR_NOT_FOUND.getMessage());
            return new AuthorizationResponse(false, AuthorizatorMessageEnum.ACTOR_NOT_FOUND.getMessage());
        }

        //        ensureCommunityExist(actionPermissionsOptional);
        Set<Permission> actionPermissionsNeeded = actionPermissionsOptional.get();

        // If action does not need permissions, authorized
        if (actionPermissionsNeeded.isEmpty()) {
            logger.info("Authorized reason: action no need permission");
            return new AuthorizationResponse(true, "");
        }

        // If user has action's permissions needed, authorized
        boolean authorized = usersPermissionsByEvent.get().containsAll(actionPermissionsNeeded);

        // If not authorized, add an authorization message
        if (!authorized) {
            logger.info("Unauthorized reason: " + AuthorizatorMessageEnum.ACTOR_HAS_NOT_PERMISSION_NEED.getMessage());
            return new AuthorizationResponse(false, AuthorizatorMessageEnum.ACTOR_HAS_NOT_PERMISSION_NEED.getMessage());
        }

        return new AuthorizationResponse(true, "");
    }
}