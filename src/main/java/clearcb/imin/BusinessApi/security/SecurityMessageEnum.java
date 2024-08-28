package clearcb.imin.BusinessApi.security;

public enum SecurityMessageEnum {
    INVALID_JWT_SIGNATURE("Invalid JWT signature: {}"),
    INVALID_JWT_TOKEN("Invalid JWT token: {}"),
    INVALID_JWT_EXPIRED("JWT token is expired: {}"),
    INVALID_JWT_UNSUPPORTED("JWT token is unsupported: {}"),
    INVALID_JWT_EMPTY("JWT claims string is empty: {}"),
    INVALID_JWT_EXCEPTION("Token exception: {}");

    private final String message;

    SecurityMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
