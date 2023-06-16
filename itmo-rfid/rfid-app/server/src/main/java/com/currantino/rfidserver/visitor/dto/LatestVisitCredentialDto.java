package com.currantino.rfidserver.visitor.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.currantino.rfidserver.credential.entity.Credential}
 */
public record LatestVisitCredentialDto(Long id, Long rfidUid) implements Serializable {
}