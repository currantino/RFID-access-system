package com.currantino.rfidserver.visitor.mapper;

import com.currantino.rfidserver.credential.mapper.CredentialMapper;
import com.currantino.rfidserver.visitor.dto.UserDto;
import com.currantino.rfidserver.visitor.dto.CreateVisitorDto;
import com.currantino.rfidserver.visitor.entity.Visitor;
import com.currantino.rfidserver.visit.VisitMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, uses = {VisitMapper.class, CredentialMapper.class})
public interface UserMapper {

    @Mapping(target="isBlocked", source = "blocked")
    UserDto toDto(Visitor visitor);

    Visitor toEntity(CreateVisitorDto createVisitorDto);

}
