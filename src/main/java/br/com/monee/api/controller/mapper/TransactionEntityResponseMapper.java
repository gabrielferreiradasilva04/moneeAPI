package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.TransactionEntity;
import br.com.monee.api.entity.dto.TransactionEntityResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionEntityResponseMapper {

    @Mapping(target = "user", ignore = true)
    TransactionEntity toEntity(TransactionEntityResponseDTO dto);

    TransactionEntityResponseDTO toDto(TransactionEntity entity);
}
