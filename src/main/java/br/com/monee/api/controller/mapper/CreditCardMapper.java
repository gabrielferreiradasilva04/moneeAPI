package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.CreditCardEntity;
import br.com.monee.api.entity.dto.CreditCardRequestDTO;
import br.com.monee.api.entity.dto.CreditCardResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankAccount", ignore = true)
    CreditCardEntity requestToEntity(CreditCardRequestDTO dto);

    @Mapping(target = "bankAccount", ignore = true)
    CreditCardEntity responseToEntity(CreditCardResponseDTO dto);

    CreditCardRequestDTO entityToRequest(CreditCardEntity entity);
    CreditCardResponseDTO entityToResponse(CreditCardEntity entity);
}
