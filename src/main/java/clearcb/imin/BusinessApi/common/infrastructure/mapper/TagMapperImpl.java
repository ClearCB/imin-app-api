package clearcb.imin.BusinessApi.common.infrastructure.mapper;

import clearcb.imin.BusinessApi.common.domain.model.Tag;
import clearcb.imin.BusinessApi.common.infrastructure.dto.TagDTO;
import clearcb.imin.BusinessApi.common.infrastructure.entity.TagEntity;
import org.springframework.stereotype.Component;

@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag toDomain(TagDTO tagDTO) {

        return new Tag(
                tagDTO.getName()
        );
    }

    @Override
    public Tag toDomainWithId(Long id, TagDTO tagDTO) {
        return new Tag(
                id,
                tagDTO.getName()
        );
    }

    @Override
    public Tag toDomain(TagEntity tagEntity) {
        return new Tag(
                tagEntity.getId(),
                tagEntity.getName()
        );
    }

    @Override
    public TagDTO toDto(Tag tag) {
        return new TagDTO(
                tag.getId(),
                tag.getName()
        );
    }

    @Override
    public TagEntity toEntity(Tag tag) {
        return new TagEntity(
                tag.getId(),
                tag.getName()
        );
    }
}
