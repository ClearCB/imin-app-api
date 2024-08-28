package clearcb.imin.BusinessApi.event.domain.model;

public enum EventMessageEnum {
    EVENT_CANT_BE_NULL("Event cannot be null."),
    EVENT_TITLE_CANT_BE_NULL("Event title cannot be null."),
    EVENT_NOT_FOUND("Event not found."),
    EVENT_CREATE_OK("Event created successfully."),
    EVENT_CREATE_KO("Event could not be created."),
    EVENT_UPDATE_OK("Event updated successfully."),
    EVENT_DELETE_OK("Event deleted successfully."),
    EVENT_DATABASE_GENERIC_ERROR("An error has occurred.");

    private final String message;

    EventMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
