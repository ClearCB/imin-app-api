package clearcb.imin.BusinessApi.auth.domain.port.input;

import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationRequest;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationResponse;

public interface AuthorizeUseCase {

    AuthorizationResponse authorize(AuthorizationRequest authorizationRequest);
}
