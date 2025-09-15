package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.UserDefaultDataDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDefaultDataDTO toDefaultDataDTO(UserEntity entity);
}
