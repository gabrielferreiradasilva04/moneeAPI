package br.com.monee.api.util.mapper;

import br.com.monee.api.domain.user.UserEntity;
import br.com.monee.api.domain.user.UserDefaultDataDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDefaultDataDTO toDefaultDataDTO(UserEntity entity);
}
