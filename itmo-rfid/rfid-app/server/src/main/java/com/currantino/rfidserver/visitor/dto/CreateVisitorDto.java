package com.currantino.rfidserver.visitor.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record CreateVisitorDto(
        @Email
        String email
) {
}
