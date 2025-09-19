package br.com.monee.api.util.mapper;

import br.com.monee.api.domain.bankAccount.BankAccountEntity;
import br.com.monee.api.domain.bankAccount.BankAccountRequestDTO;
import br.com.monee.api.domain.bankAccount.BankAccountResponseDTO;
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
