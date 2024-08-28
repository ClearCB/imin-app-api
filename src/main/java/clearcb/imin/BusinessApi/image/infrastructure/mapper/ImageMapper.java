package clearcb.imin.BusinessApi.image.infrastructure.mapper;

import clearcb.imin.BusinessApi.image.domain.model.Image;
import clearcb.imin.BusinessApi.image.infrastructure.dto.ImageDTO;
import clearcb.imin.BusinessApi.image.infrastructure.entity.ImageEntity;

public interface ImageMapper {

    Image toDomain(ImageDTO imageDTO);

    Image toDomainWithId(String id, ImageDTO imageDTO);

    Image toDomain(ImageEntity imageEntity);

    ImageDTO toDto(Image image);

    ImageEntity toEntity(Image image);

}
