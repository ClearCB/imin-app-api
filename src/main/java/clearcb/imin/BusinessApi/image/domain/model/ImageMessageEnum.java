package clearcb.imin.BusinessApi.image.domain.model;

public enum ImageMessageEnum {
    IMAGE_CANT_BE_NULL("Image cannot be null."),
    IMAGE_TITLE_CANT_BE_NULL("Image title cannot be null."),
    IMAGE_NOT_FOUND("Image not found."),
    IMAGE_CREATE_OK("Image created successfully."),
    IMAGE_CREATE_KO("Image could not be created."),
    IMAGE_UPDATE_OK("Image updated successfully."),
    IMAGE_DELETE_OK("Image deleted successfully."),
    IMAGE_DATABASE_GENERIC_ERROR("An error has occurred.");

    private final String message;

    ImageMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
