package com.currantino.rfidserver.credential.service;

import com.currantino.rfidserver.credential.dto.CreateCredentialDto;
import com.currantino.rfidserver.credential.dto.CredentialDto;
import com.currantino.rfidserver.credential.entity.Credential;
import com.currantino.rfidserver.credential.mapper.CredentialMapper;
import com.currantino.rfidserver.credential.repository.CredentialRepository;
import com.currantino.rfidserver.exception.CredentialNotFoundException;
import com.currantino.rfidserver.visitor.entity.Visitor;
import com.currantino.rfidserver.visitor.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CredentialService {
    private final CredentialRepository credentialRepository;
    private final VisitorRepository visitorRepository;
    private final CredentialMapper credentialMapper;

    @Transactional
    public CreateCredentialResponseDto createCredential(CreateCredentialDto createCredentialDto) {

        Credential credential = credentialMapper.toEntity(createCredentialDto);
        Credential savedCredential = credentialRepository.save(credential);
        if (createCredentialDto.owner() != null) {
            Visitor owner = Visitor.builder()
                    .email(createCredentialDto.owner().email())
                    .credential(savedCredential)
                    .build();
            visitorRepository.save(owner);
        }
        return credentialMapper.toCreateResponseDto(savedCredential);
    }


    public List<CredentialDto> getCredentials() {
        return credentialRepository.findAll().stream().map(credentialMapper::toDto).collect(toList());
    }

    @Transactional
    public CredentialDto blockCredentialById(Long id) {
        Credential credential = credentialRepository.findById(id)
                .orElseThrow(() -> new CredentialNotFoundException("Пропуск не найден."));
        credential.setBlocked(true);
        Credential saved = credentialRepository.save(credential);
        return credentialMapper.toDto(saved);
    }

    @Transactional
    public CredentialDto unblockCredentialById(Long id) {
        Credential credential = credentialRepository.findById(id)
                .orElseThrow(() -> new CredentialNotFoundException("Пропуск не найден."));
        credential.setBlocked(false);
        Credential saved = credentialRepository.save(credential);
        return credentialMapper.toDto(saved);
    }

    @Transactional
    public void deleteCredentialById(Long id) {
        if (!credentialRepository.existsById(id)) {
            throw new CredentialNotFoundException("Пропуск не найден.");
        }
        credentialRepository.deleteById(id);
    }
}
