package com.currantino.rfidserver.exception;

public class CredentialNotFoundException extends ResourceNotFoundException {
    public CredentialNotFoundException(String message) {
        super(message);
    }
}
