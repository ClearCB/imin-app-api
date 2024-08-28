package clearcb.imin.BusinessApi.image.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.image.domain.model.Image;
import clearcb.imin.BusinessApi.image.domain.port.input.GetImageFromObjectUseCase;
import clearcb.imin.BusinessApi.image.domain.port.output.ImageRepositoryPort;

import java.util.Set;
import java.util.UUID;

public class GetImageFromObjectUseCaseImpl implements GetImageFromObjectUseCase {

    private final ImageRepositoryPort imageRepositoryPort;
    private final Logger logger;

    public GetImageFromObjectUseCaseImpl(ImageRepositoryPort imageRepositoryPort, Logger logger) {
        this.imageRepositoryPort = imageRepositoryPort;
        this.logger = logger;
    }

    @Override
    public Set<Image> getImagesFromObject(UUID objectId) {
        return imageRepositoryPort.getObjectImages(objectId);
    }
}