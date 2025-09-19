package br.com.monee.api.util.mapper;

import br.com.monee.api.domain.transaction.category.TransactionCategoryEntity;
import br.com.monee.api.domain.transaction.category.TransactionCategoryRequestDTO;
import br.com.monee.api.domain.transaction.category.TransactionCategoryResponseDTO;
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
