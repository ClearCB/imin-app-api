package clearcb.imin.BusinessApi.image.infrastructure.repository;

import clearcb.imin.BusinessApi.common.domain.exception.DatabaseError;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import clearcb.imin.BusinessApi.image.domain.model.Image;
import clearcb.imin.BusinessApi.image.domain.model.ImageMessageEnum;
import clearcb.imin.BusinessApi.image.domain.port.output.ImageRepositoryPort;
import clearcb.imin.BusinessApi.image.infrastructure.entity.ImageEntity;
import clearcb.imin.BusinessApi.image.infrastructure.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaImageRepositoryAdapter implements ImageRepositoryPort {

    private final JpaImageRepository jpaImageRepository;
    private final Logger logger;
    private final ImageMapper imageMapper;


    @Override
    public Set<Image> getObjectImages(UUID objectId) {
        try {

            return jpaImageRepository.findByEventId(objectId).stream().map(imageMapper::toDomain).collect(Collectors.toSet());

        } catch (Exception e) {
            logger.error("Error retreiving image: " + e.getMessage());
            throw new DatabaseError(ImageMessageEnum.IMAGE_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public void deleteImage(UUID imageId) {
        try {

            jpaImageRepository
                    .deleteById(imageId);

        } catch (Exception e) {
            logger.error("Error deleting image: " + e.getMessage());
            throw new DatabaseError(ImageMessageEnum.IMAGE_DATABASE_GENERIC_ERROR.getMessage());
        }

    }

    @Override
    public void uploadImage(Image image, UUID objectId) {
        try {

            Set<ImageEntity> imageEntitySet = jpaImageRepository.findByEventId(objectId);

            if (!imageEntitySet.isEmpty()){
                Optional<ImageEntity> imageEntityOptional=  imageEntitySet.stream().filter(i -> {return image.isMain();}).findFirst();
                imageEntityOptional.ifPresent(entity -> {
                    entity.setBytes(image.getBytes());
                    jpaImageRepository.save(entity);
                });


            } else {

                ImageEntity imageEntity = imageMapper.toEntity(image);
                imageEntity.setEventId(objectId);

                jpaImageRepository
                        .save(imageEntity);

            }


        } catch (Exception e) {
            logger.error("Error uploading image: " + e.getMessage());
            throw new DatabaseError(ImageMessageEnum.IMAGE_DATABASE_GENERIC_ERROR.getMessage());
        }

    }
}
