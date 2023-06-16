package com.currantino.rfidserver.visitor.service;

import com.currantino.rfidserver.visitor.dto.LatestVisitCredentialDto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.currantino.rfidserver.visit.entity.Visit}
 */
public record LatestVisitDto(Long id, LocalDateTime visitDatetime, LatestVisitCredentialDto credential) {
}