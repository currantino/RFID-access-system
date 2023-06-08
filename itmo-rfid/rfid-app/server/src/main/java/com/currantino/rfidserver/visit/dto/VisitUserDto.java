package com.currantino.rfidserver.visit.dto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.currantino.rfidserver.visit.entity.Visit}
 */
public record VisitUserDto(Long id,
                           LocalDateTime time) {
}