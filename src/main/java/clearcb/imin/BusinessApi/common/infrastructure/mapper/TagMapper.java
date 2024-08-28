package clearcb.imin.BusinessApi.common.infrastructure.mapper;

import clearcb.imin.BusinessApi.common.domain.model.Tag;
import clearcb.imin.BusinessApi.common.infrastructure.dto.TagDTO;
import clearcb.imin.BusinessApi.common.infrastructure.entity.TagEntity;


public interface TagMapper {

    Tag toDomain(TagDTO tagDTO);

    Tag toDomainWithId(Long id, TagDTO tagDTO);

    Tag toDomain(TagEntity tagEntity);

    TagDTO toDto(Tag tag);

    TagEntity toEntity(Tag tag);

}
