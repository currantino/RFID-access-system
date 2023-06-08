package com.currantino.rfidserver.visit;

import com.currantino.rfidserver.visit.dto.VisitUserDto;
import com.currantino.rfidserver.visit.entity.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING)
public interface VisitMapper {

    VisitUserDto toDto(Visit visit);

    List<VisitUserDto> toDto(List<Visit> visit);
}
