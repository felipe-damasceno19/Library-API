package io.github.felipe_damasceno19.libraryapi.controller.mappers;

import io.github.felipe_damasceno19.libraryapi.controller.dto.UserDTO;
import io.github.felipe_damasceno19.libraryapi.model.SystemUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    SystemUser toEntity(UserDTO dto);
}
