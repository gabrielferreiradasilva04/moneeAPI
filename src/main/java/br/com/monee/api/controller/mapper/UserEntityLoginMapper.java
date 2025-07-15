package br.com.monee.api.controller.mapper;

import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.UserEntityLoginDTO;
import br.com.monee.api.entity.dto.UserEntityRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityLoginMapper {

    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "userRole", ignore = true)
    @Mapping(target = "profilePhotoUrl", ignore = true)
    UserEntity toEntity (UserEntityLoginDTO dto);

    UserEntityLoginDTO toDto(UserEntity userEntity);


}
