package clearcb.imin.BusinessApi.image.domain.port.output;

import clearcb.imin.BusinessApi.image.domain.model.Image;

import java.util.Set;
import java.util.UUID;

public interface ImageRepositoryPort {

    Set<Image> getObjectImages(UUID objectId);

    void deleteImage(UUID imageId);

    void uploadImage(Image image, UUID objectId);
}
