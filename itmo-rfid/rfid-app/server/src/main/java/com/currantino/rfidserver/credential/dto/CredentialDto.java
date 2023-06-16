package com.currantino.rfidserver.credential.dto;

public record CredentialDto(Long id, Long rfidUid, VisitorCredentialDto owner, Boolean isBlocked,
                            VisitCredentialDto lastVisit) {
}
