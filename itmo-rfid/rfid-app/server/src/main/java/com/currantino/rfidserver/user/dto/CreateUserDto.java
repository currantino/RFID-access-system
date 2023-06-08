package com.currantino.rfidserver.user.dto;

import javax.validation.constraints.NotEmpty;

public record CreateUserDto(
        @NotEmpty
        String username
) {
}
