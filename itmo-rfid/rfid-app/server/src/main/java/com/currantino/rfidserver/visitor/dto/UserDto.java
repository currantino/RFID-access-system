package com.currantino.rfidserver.visitor.dto;

import com.currantino.rfidserver.credential.dto.CredentialUserDto;
import com.currantino.rfidserver.visitor.service.LatestVisitDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    Long id;
    String email;
    CredentialUserDto rfid;
    Boolean isBlocked;
    List<LatestVisitDto> visits;
}