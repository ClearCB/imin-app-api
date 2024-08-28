package clearcb.imin.BusinessApi.image.application.usecase;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.image.domain.port.input.DeleteImageUseCase;
import clearcb.imin.BusinessApi.image.domain.port.output.ImageRepositoryPort;

import java.util.UUID;

public class DeleteImageUseCaseImpl implements DeleteImageUseCase {

    private final ImageRepositoryPort imageRepositoryPort;
    private final Logger logger;

    public DeleteImageUseCaseImpl(ImageRepositoryPort imageRepositoryPort,
                                  Logger logger) {
        this.imageRepositoryPort = imageRepositoryPort;
        this.logger = logger;
    }


    @Override
    public void deleteImage(UUID imageId) {
        imageRepositoryPort.deleteImage(imageId);
    }
}