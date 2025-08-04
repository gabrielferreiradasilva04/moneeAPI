package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.TagEntity;
import br.com.monee.api.entity.dto.TagDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagEntity toEntity(TagDTO dto);
    TagDTO toDTO(TagEntity entity);
}
