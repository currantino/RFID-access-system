package com.currantino.rfidserver.credential.service;

import java.io.Serializable;

/**
 * DTO for {@link com.currantino.rfidserver.credential.entity.Credential}
 */
public record CreateCredentialResponseDto(Long id, Long rfidUid) implements Serializable {
}