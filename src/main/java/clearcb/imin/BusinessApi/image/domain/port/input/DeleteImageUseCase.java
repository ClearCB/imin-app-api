package clearcb.imin.BusinessApi.image.domain.port.input;

import java.util.UUID;

public interface DeleteImageUseCase {

    void deleteImage(UUID imageId);
}
