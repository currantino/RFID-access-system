package com.currantino.rfidserver.credential.controller;

import com.currantino.rfidserver.credential.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CredentialController {
    private final CredentialService credentialService;
}
