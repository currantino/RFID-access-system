package com.currantino.rfidserver.visit;

import com.currantino.rfidserver.visit.dto.VisitDto;
import com.currantino.rfidserver.visit.dto.VisitUserDto;
import com.currantino.rfidserver.visit.entity.Visit;
import com.currantino.rfidserver.visitor.mapper.VisitorMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING, uses = {VisitorMapper.class})
public interface VisitMapper {

    VisitUserDto toVisitUserDto(Visit visit);

    List<VisitUserDto> toVisitUserDto(List<Visit> visit);

    VisitDto toDto(Visit visit);
}
