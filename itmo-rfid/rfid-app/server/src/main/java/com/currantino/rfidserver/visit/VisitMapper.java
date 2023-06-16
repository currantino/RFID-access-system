package com.currantino.rfidserver.visit;

import com.currantino.rfidserver.credential.mapper.CredentialMapper;
import com.currantino.rfidserver.visit.dto.VisitDto;
import com.currantino.rfidserver.visit.dto.VisitUserDto;
import com.currantino.rfidserver.visit.entity.Visit;
import com.currantino.rfidserver.visitor.mapper.VisitorMapper;
import com.currantino.rfidserver.visitor.service.LatestVisitDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING, uses = {VisitorMapper.class, CredentialMapper.class})
public interface VisitMapper {

    VisitUserDto toVisitUserDto(Visit visit);

    List<VisitUserDto> toVisitUserDto(List<Visit> visit);

    VisitDto toDto(Visit visit);

    LatestVisitDto toLatestVisitDto(Visit visit);

}
