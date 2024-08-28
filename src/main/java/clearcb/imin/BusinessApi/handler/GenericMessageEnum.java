package clearcb.imin.BusinessApi.handler;

public enum GenericMessageEnum {
    VALIDATION_FAILED("Validation failed"),
    UNEXPECTED_ERROR("An unexpected error occurred"),
    GENERIC_MESSAGE_ERROR("An error has occurred."),
    CONSTRAINT_VIOLATION("Constraint violation"),
    INVALID_ARGUMENT_TYPE("Invalid argument type"),
    INVALID_UUID_FORMAT("Invalid UUID format"),
    UNEXPECTED_DATABASE_ERROR("An unexpected error occurred in database"),
    BAD_CREDENTIALS("Bad credentials."),
    GENERIC_VALIDATION_MESSAGE("Params are not valid."),
    GENERIC_NOT_FOUND_MESSAGE("Resource not found.");

    private final String message;

    GenericMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
