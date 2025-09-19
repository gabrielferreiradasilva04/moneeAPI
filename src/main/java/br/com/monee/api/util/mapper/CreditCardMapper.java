package br.com.monee.api.util.mapper;

import br.com.monee.api.domain.creditCard.CreditCardEntity;
import br.com.monee.api.domain.creditCard.CreditCardRequestDTO;
import br.com.monee.api.domain.creditCard.CreditCardResponseDTO;
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
