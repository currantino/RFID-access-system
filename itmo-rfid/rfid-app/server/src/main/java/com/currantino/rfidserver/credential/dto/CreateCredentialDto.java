package com.currantino.rfidserver.credential.dto;

import com.currantino.rfidserver.visitor.dto.CreateVisitorDto;

import javax.validation.constraints.NotNull;

public record CreateCredentialDto(@NotNull Long rfidUid, CreateVisitorDto owner) {
}
