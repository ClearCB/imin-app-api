package clearcb.imin.BusinessApi.image.domain.port.input;

import clearcb.imin.BusinessApi.image.domain.model.Image;

import java.util.Set;
import java.util.UUID;

public interface UploadImageUseCase {

    void uploadImage(Set<Image> images, UUID objectId);
}
