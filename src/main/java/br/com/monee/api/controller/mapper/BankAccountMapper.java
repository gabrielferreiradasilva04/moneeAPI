package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.BankAccountEntity;
import br.com.monee.api.entity.dto.BankAccountRequestDTO;
import br.com.monee.api.entity.dto.BankAccountResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    @Mapping(target = "user", ignore = true)
    BankAccountEntity responseToEntity(BankAccountResponseDTO responseDTO);

    @Mapping(target = "user", ignore = true)
    BankAccountEntity requestToEntity (BankAccountRequestDTO requestDTO);

    BankAccountResponseDTO toResponseDto(BankAccountEntity entity );
}
