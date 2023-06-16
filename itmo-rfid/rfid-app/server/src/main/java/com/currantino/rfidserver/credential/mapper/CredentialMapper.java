package com.currantino.rfidserver.credential.mapper;

import com.currantino.rfidserver.credential.dto.CreateCredentialDto;
import com.currantino.rfidserver.credential.dto.CredentialDto;
import com.currantino.rfidserver.credential.dto.CredentialUserDto;
import com.currantino.rfidserver.credential.entity.Credential;
import com.currantino.rfidserver.credential.service.CreateCredentialResponseDto;
import com.currantino.rfidserver.visitor.dto.LatestVisitCredentialDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CredentialMapper {
    Credential toEntity(CreateCredentialDto createCredentialDto);

    CredentialUserDto toUserDto(Credential credential);

    CredentialDto toDto(Credential credential);

    Credential toEntity(CreateCredentialResponseDto createCredentialResponseDto);

    CreateCredentialResponseDto toCreateResponseDto(Credential credential);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Credential partialUpdate(CreateCredentialResponseDto createCredentialResponseDto, @MappingTarget Credential credential);

    LatestVisitCredentialDto toLatestVisitDto(Credential credential);
}
