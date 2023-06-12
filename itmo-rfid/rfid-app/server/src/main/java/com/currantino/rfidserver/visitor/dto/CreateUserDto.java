package com.currantino.rfidserver.visitor.dto;

import javax.validation.constraints.NotEmpty;

public record CreateUserDto(
        @NotEmpty
        String username
) {
}
