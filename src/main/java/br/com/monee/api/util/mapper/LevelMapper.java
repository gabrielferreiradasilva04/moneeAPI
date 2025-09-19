package br.com.monee.api.util.mapper;

import br.com.monee.api.domain.level.LevelEntity;
import br.com.monee.api.domain.level.LevelRequestDTO;
import br.com.monee.api.domain.level.LevelResponseDTO;
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
