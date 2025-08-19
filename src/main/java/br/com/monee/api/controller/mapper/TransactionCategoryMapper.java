package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.TransactionCategoryEntity;
import br.com.monee.api.entity.dto.TransactionCategoryRequestDTO;
import br.com.monee.api.entity.dto.TransactionCategoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    TransactionCategoryEntity requestToEntity (TransactionCategoryRequestDTO dto);
    TransactionCategoryEntity responseToEntity (TransactionCategoryResponseDTO dto);


    TransactionCategoryResponseDTO entityToResponse (TransactionCategoryEntity entity);
    TransactionCategoryRequestDTO entityToRequest (TransactionCategoryEntity entity);
}
