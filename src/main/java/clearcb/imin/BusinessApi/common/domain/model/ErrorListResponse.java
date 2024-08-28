package clearcb.imin.BusinessApi.common.domain.model;

import java.util.List;

public record ErrorListResponse(int status, String error, List<String> messages) {
}
