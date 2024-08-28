package clearcb.imin.BusinessApi.image.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.image.domain.model.Image;
import clearcb.imin.BusinessApi.image.domain.port.input.UploadImageUseCase;
import clearcb.imin.BusinessApi.image.domain.port.output.ImageRepositoryPort;

import java.util.Set;
import java.util.UUID;

public class UploadImageUseCaseImpl implements UploadImageUseCase {

    private final ImageRepositoryPort imageRepositoryPort;
    private final Logger logger;

    public UploadImageUseCaseImpl(ImageRepositoryPort imageRepositoryPort,
                                  Logger logger) {
        this.imageRepositoryPort = imageRepositoryPort;
        this.logger = logger;
    }

    @Override
    public void uploadImage(Set<Image> images, UUID objectId) {

        for (Image image : images) {
            imageRepositoryPort.uploadImage(image, objectId);
            logger.info("Image with id: " + image.getId() + " uploaded.");
        }

    }
}