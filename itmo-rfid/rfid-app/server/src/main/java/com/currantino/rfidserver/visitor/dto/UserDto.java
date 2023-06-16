package com.currantino.rfidserver.visitor.dto;

import com.currantino.rfidserver.credential.dto.CredentialUserDto;
import com.currantino.rfidserver.visit.dto.VisitUserDto;
import com.currantino.rfidserver.visitor.entity.Visitor;

import java.util.List;

/**
 * DTO for {@link Visitor}
 */
public record UserDto(Long id,
                      String email,
                      CredentialUserDto rfid,
                      Boolean isBlocked,
                      List<VisitUserDto> visits) {
}