package clearcb.imin.BusinessApi.auth.domain.model;


public enum AuthorizatorMessageEnum {

    ACTION_NOT_FOUND("The action was not found."),
    ERROR_RETRIEVING_ACTION_PERMISSIONS("An error has occurred while trying to get actions permissions."),
    ERROR_RETRIEVING_ACTOR_PERMISSIONS("An error has occurred while trying to get actor permissions."),
    ACTOR_HAS_NOT_PERMISSION_NEED("The actor has not the permission to execute the action."),
    ACTOR_NOT_FOUND("The actor was not found."),
    GENERIC_DATABASE_ERROR_MESSAGE("An error has occurred with database."),
    BAD_CREDENTIALS("Bad credentials."),
    LOGIN_OK("Login success"),
    REGISTER_OK("Register success"),
    NO_STRATEGY_FOR_CURRENT_CONTEXT("No strategy set for the current context.");

    private final String message;

    AuthorizatorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
