package clearcb.imin.BusinessApi.common.domain.model;

public record ErrorResponse(int status, String error, String message) {
}
