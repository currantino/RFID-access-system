package com.currantino.rfidserver.user.mapper;

import com.currantino.rfidserver.credential.mapper.CredentialMapper;
import com.currantino.rfidserver.user.controller.UserDto;
import com.currantino.rfidserver.user.dto.CreateUserDto;
import com.currantino.rfidserver.user.entity.User;
import com.currantino.rfidserver.visit.VisitMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, uses = {VisitMapper.class, CredentialMapper.class})
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(CreateUserDto createUserDto);

}
