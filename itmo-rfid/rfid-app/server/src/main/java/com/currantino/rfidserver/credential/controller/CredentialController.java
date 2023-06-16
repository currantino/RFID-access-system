package com.currantino.rfidserver.credential.controller;

import com.currantino.rfidserver.credential.dto.CreateCredentialDto;
import com.currantino.rfidserver.credential.dto.CredentialDto;
import com.currantino.rfidserver.credential.service.CreateCredentialResponseDto;
import com.currantino.rfidserver.credential.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/credentials")
@RestController
@CrossOrigin(
        origins = {"*"}
)
@RequiredArgsConstructor
public class CredentialController {
    private final CredentialService credentialService;

    @PostMapping
    public CreateCredentialResponseDto createCredential(
            @Valid
            @RequestBody
            CreateCredentialDto createCredentialDto
    ) {
        return credentialService.createCredential(createCredentialDto);
    }

    @GetMapping
    public List<CredentialDto> getCredentials() {
        return credentialService.getCredentials();
    }

    @PostMapping("/{id}/block")
    public CredentialDto blockCredentialById(
            @PathVariable(name = "id")
            Long id
    ) {
        return credentialService.blockCredentialById(id);
    }

    @PostMapping("/{id}/unblock")
    public CredentialDto unblockCredentialById(
            @PathVariable(name = "id")
            Long id
    ) {
        return credentialService.unblockCredentialById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredentialById(
            @PathVariable(name = "id")
            Long id
    ) {
        credentialService.deleteCredentialById(id);
        return ResponseEntity.noContent().build();
    }
}
