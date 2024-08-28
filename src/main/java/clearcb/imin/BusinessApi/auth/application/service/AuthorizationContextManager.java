package clearcb.imin.BusinessApi.auth.application.service;

import clearcb.imin.BusinessApi.auth.domain.model.ActionContextEnum;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationRequest;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationResponse;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizatorMessageEnum;
import clearcb.imin.BusinessApi.auth.domain.port.input.AuthorizeUseCase;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;

import java.util.Map;

public class AuthorizationContextManager {

    private final Map<ActionContextEnum, AuthorizeUseCase> strategies;
    private final Logger logger;
    private AuthorizeUseCase activeStrategy;

    public AuthorizationContextManager(Map<ActionContextEnum, AuthorizeUseCase> strategies, Logger logger) {
        this.strategies = strategies;
        this.logger = logger;

    }

    private void setStrategy(ActionContextEnum actionContext) {
        this.activeStrategy = strategies.get(actionContext);
    }

    public AuthorizationResponse executeStrategy(ActionContextEnum actionContext, AuthorizationRequest authorizationRequest) {
        // Set the active strategy based on the action context
        this.setStrategy(actionContext);

        // If no strategy is available, throw an exception
        if (activeStrategy == null) {
            logger.error(AuthorizatorMessageEnum.NO_STRATEGY_FOR_CURRENT_CONTEXT.getMessage());
            throw new IllegalStateException(AuthorizatorMessageEnum.NO_STRATEGY_FOR_CURRENT_CONTEXT.getMessage());
        }

        // Execute the active strategy and return the result
        return this.activeStrategy.authorize(authorizationRequest);
    }
}