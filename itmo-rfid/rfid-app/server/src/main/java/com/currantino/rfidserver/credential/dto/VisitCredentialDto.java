package com.currantino.rfidserver.credential.dto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.currantino.rfidserver.visit.entity.Visit}
 */
public record VisitCredentialDto(Long id, LocalDateTime visitDatetime, Boolean passed) {
}