package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.TransactionEntity;
import br.com.monee.api.entity.dto.TransactionRequestDTO;
import br.com.monee.api.entity.dto.TransactionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "user", ignore = true)
    TransactionEntity responseToEntity(TransactionResponseDTO dto );

    @Mapping(target = "user", ignore = true)
    TransactionEntity requestToEntity(TransactionRequestDTO dto );

    TransactionResponseDTO toResponseDto( TransactionEntity entity );

    TransactionRequestDTO toRequestDto( TransactionEntity entity );
}
