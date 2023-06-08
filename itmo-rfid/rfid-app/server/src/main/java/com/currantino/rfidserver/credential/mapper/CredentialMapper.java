package com.currantino.rfidserver.credential.mapper;

import com.currantino.rfidserver.credential.dto.CredentialUserDto;
import com.currantino.rfidserver.credential.entity.Credential;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CredentialMapper {

    CredentialUserDto toDto(Credential credential);
}
