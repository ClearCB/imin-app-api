package clearcb.imin.BusinessApi.auth.domain.port.input;

import clearcb.imin.BusinessApi.auth.domain.model.AuthResponse;
import clearcb.imin.BusinessApi.auth.domain.model.LoginRequest;

public interface LoginUseCase {
    AuthResponse login(LoginRequest loginRequest);
}
