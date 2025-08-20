package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.LevelEntity;
import br.com.monee.api.entity.dto.LevelRequestDTO;
import br.com.monee.api.entity.dto.LevelResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LevelMapper {

    @Mapping(target = "id", ignore = true)
    LevelEntity requestToEntity(LevelRequestDTO dto);
    LevelEntity responseToEntity(LevelResponseDTO dto);

    LevelResponseDTO entityToResponse(LevelEntity entity);
    LevelRequestDTO entityToRequest(LevelEntity entity);
}
