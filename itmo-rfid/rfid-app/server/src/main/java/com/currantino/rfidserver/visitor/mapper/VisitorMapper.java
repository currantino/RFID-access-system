package com.currantino.rfidserver.visitor.mapper;

import com.currantino.rfidserver.visitor.dto.VisitorVisitDto;
import com.currantino.rfidserver.visitor.entity.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface VisitorMapper {
    VisitorVisitDto toVisitorVisitDto(Visitor visitor);
}
