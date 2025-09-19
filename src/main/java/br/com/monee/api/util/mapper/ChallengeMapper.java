package br.com.monee.api.util.mapper;

import br.com.monee.api.domain.challenge.ChallengeEntity;
import br.com.monee.api.domain.challenge.ChallengeRequestDTO;
import br.com.monee.api.domain.challenge.ChallengeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    ChallengeEntity requestToEntity(ChallengeRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    ChallengeEntity responseToEntity(ChallengeResponseDTO dto);

    ChallengeResponseDTO entityToResponse(ChallengeEntity entity);
    ChallengeRequestDTO entityToRequest(ChallengeEntity entity);
}
