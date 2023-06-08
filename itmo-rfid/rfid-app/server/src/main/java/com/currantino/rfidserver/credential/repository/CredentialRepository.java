package com.currantino.rfidserver.credential.repository;

import com.currantino.rfidserver.credential.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
}