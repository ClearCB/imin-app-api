package clearcb.imin.BusinessApi.image.application.service;

import clearcb.imin.BusinessApi.image.domain.model.Image;
import clearcb.imin.BusinessApi.image.domain.port.input.DeleteImageUseCase;
import clearcb.imin.BusinessApi.image.domain.port.input.GetImageFromObjectUseCase;
import clearcb.imin.BusinessApi.image.domain.port.input.UploadImageUseCase;

import java.util.Set;
import java.util.UUID;

public class ImageService implements DeleteImageUseCase, UploadImageUseCase, GetImageFromObjectUseCase {

    private final DeleteImageUseCase deleteImageUseCase;
    private final UploadImageUseCase uploadImageUseCase;
    private final GetImageFromObjectUseCase getImageFromObjectUseCase;

    public ImageService(DeleteImageUseCase deleteImageUseCase, UploadImageUseCase uploadImageUseCase, GetImageFromObjectUseCase getImageFromObjectUseCase) {
        this.deleteImageUseCase = deleteImageUseCase;
        this.uploadImageUseCase = uploadImageUseCase;
        this.getImageFromObjectUseCase = getImageFromObjectUseCase;
    }

    @Override
    public void deleteImage(UUID imageId) {
        deleteImageUseCase.deleteImage(imageId);
    }

    @Override
    public Set<Image> getImagesFromObject(UUID objectId) {
        return getImageFromObjectUseCase.getImagesFromObject(objectId);
    }

    @Override
    public void uploadImage(Set<Image> images, UUID objectId) {
        uploadImageUseCase.uploadImage(images, objectId);
    }
}