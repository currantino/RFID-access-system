package com.currantino.rfidserver.user.controller;

import com.currantino.rfidserver.credential.dto.CredentialUserDto;
import com.currantino.rfidserver.visit.dto.VisitUserDto;

import java.util.List;

/**
 * DTO for {@link com.currantino.rfidserver.user.entity.User}
 */
public record UserDto(Long id,
                      String username,
                      CredentialUserDto rfid,
                      List<VisitUserDto> visits) {
}