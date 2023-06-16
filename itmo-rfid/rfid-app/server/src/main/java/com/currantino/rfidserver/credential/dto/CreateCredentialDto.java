package com.currantino.rfidserver.credential.dto;

import javax.validation.constraints.NotNull;

public record CreateCredentialDto(@NotNull Long rfidUid) {
}
