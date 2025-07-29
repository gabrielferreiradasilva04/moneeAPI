package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.BankAccountEntity;
import br.com.monee.api.entity.dto.BankAccountResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BankAccountResponseMapper {

    @Mapping(target = "user", ignore = true)
    BankAccountEntity toEntity ( BankAccountResponseDTO responseDTO );

    BankAccountResponseDTO toDto ( BankAccountEntity entity );
}
