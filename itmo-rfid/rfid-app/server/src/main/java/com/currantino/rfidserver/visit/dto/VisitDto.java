package com.currantino.rfidserver.visit.dto;

import com.currantino.rfidserver.visitor.dto.VisitorVisitDto;

public record VisitDto(Long id, Long rfidUid, VisitorVisitDto visitor, Boolean passed) {
}
