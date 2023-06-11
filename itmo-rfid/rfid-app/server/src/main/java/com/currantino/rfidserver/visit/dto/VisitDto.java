package com.currantino.rfidserver.visit.dto;

import com.currantino.rfidserver.user.dto.UserVisitDto;

public record VisitDto(Long id, Long rfidUid, UserVisitDto visitor, Boolean passed) {
}
