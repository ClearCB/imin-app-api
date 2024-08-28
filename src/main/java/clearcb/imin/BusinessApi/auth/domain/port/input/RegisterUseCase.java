package clearcb.imin.BusinessApi.auth.domain.port.input;

import clearcb.imin.BusinessApi.auth.domain.model.AuthResponse;
import clearcb.imin.BusinessApi.auth.domain.model.RegisterRequest;

public interface RegisterUseCase {
    AuthResponse register(RegisterRequest registerRequest);
}
