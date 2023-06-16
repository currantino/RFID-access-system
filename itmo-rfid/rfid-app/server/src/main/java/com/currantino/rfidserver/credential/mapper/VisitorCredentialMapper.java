package com.currantino.rfidserver.credential.mapper;

import com.currantino.rfidserver.credential.dto.VisitorCredentialDto;
import com.currantino.rfidserver.visitor.entity.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VisitorCredentialMapper {

    VisitorCredentialDto toDto(Visitor visitor);

}