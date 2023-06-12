package com.currantino.rfidserver.visitor.controller;

import com.currantino.rfidserver.credential.dto.CredentialUserDto;
import com.currantino.rfidserver.visitor.entity.Visitor;
import com.currantino.rfidserver.visit.dto.VisitUserDto;

import java.util.List;

/**
 * DTO for {@link Visitor}
 */
public record UserDto(Long id,
                      String username,
                      CredentialUserDto rfid,
                      List<VisitUserDto> visits) {
}