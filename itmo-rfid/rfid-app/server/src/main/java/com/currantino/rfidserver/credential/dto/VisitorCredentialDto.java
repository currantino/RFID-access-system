package com.currantino.rfidserver.credential.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.currantino.rfidserver.visitor.entity.Visitor}
 */
public record VisitorCredentialDto(Long id, String email, Boolean blocked) implements Serializable {
}