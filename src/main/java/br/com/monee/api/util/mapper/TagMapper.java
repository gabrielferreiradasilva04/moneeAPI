package br.com.monee.api.util.mapper;

import br.com.monee.api.domain.transaction.tag.TagEntity;
import br.com.monee.api.domain.transaction.tag.TagDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagEntity toEntity(TagDTO dto);
    TagDTO toDTO(TagEntity entity);
}
