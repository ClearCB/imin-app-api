package clearcb.imin.BusinessApi.image.infrastructure.mapper;

import clearcb.imin.BusinessApi.image.domain.model.Image;
import clearcb.imin.BusinessApi.image.infrastructure.dto.ImageDTO;
import clearcb.imin.BusinessApi.image.infrastructure.entity.ImageEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageMapperImpl implements ImageMapper {

    @Override
    public Image toDomain(ImageDTO imageDTO) {

        return new Image(
                imageDTO.getBytes(),
                imageDTO.isMain()
        );

    }

    @Override
    public Image toDomainWithId(String id, ImageDTO imageDTO) {
        return new Image(
                UUID.fromString(id),
                imageDTO.getBytes(),
                imageDTO.isMain()
        );
    }

    @Override
    public Image toDomain(ImageEntity imageEntity) {

        return new Image(
                imageEntity.getId(),
                imageEntity.getBytes(),
                imageEntity.isMain()
        );
    }

    @Override
    public ImageDTO toDto(Image image) {

        return new ImageDTO(
                image.getId().toString(),
                image.getBytes(),
                image.isMain()
        );
    }

    @Override
    public ImageEntity toEntity(Image image) {

        return new ImageEntity(
                image.getId(),
                image.getBytes(),
                image.isMain()
        );
    }

}
