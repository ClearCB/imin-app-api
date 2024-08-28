package clearcb.imin.BusinessApi.config;

import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.image.application.service.ImageService;
import clearcb.imin.BusinessApi.image.application.usecase.DeleteImageUseCaseImpl;
import clearcb.imin.BusinessApi.image.application.usecase.GetImageFromObjectUseCaseImpl;
import clearcb.imin.BusinessApi.image.application.usecase.UploadImageUseCaseImpl;
import clearcb.imin.BusinessApi.image.domain.port.input.DeleteImageUseCase;
import clearcb.imin.BusinessApi.image.domain.port.input.GetImageFromObjectUseCase;
import clearcb.imin.BusinessApi.image.domain.port.input.UploadImageUseCase;
import clearcb.imin.BusinessApi.image.domain.port.output.ImageRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanImageConfiguration {

    @Bean
    public UploadImageUseCase uploadImageUseCase(ImageRepositoryPort imageRepositoryPort, Logger logger) {
        return new UploadImageUseCaseImpl(imageRepositoryPort, logger);
    }


    @Bean
    public DeleteImageUseCase deleteImageUseCase(ImageRepositoryPort imageRepositoryPort, Logger logger) {
        return new DeleteImageUseCaseImpl(imageRepositoryPort, logger);
    }

    @Bean
    public GetImageFromObjectUseCase getImageFromObjectUseCase(ImageRepositoryPort imageRepositoryPort, Logger logger) {
        return new GetImageFromObjectUseCaseImpl(imageRepositoryPort, logger);
    }

    @Bean
    public ImageService imageService(UploadImageUseCase uploadImageUseCase,
                                     DeleteImageUseCase deleteImageUseCase,
                                     GetImageFromObjectUseCase getImageFromObjectUseCase) {
        return new ImageService(deleteImageUseCase, uploadImageUseCase, getImageFromObjectUseCase);
    }

}
