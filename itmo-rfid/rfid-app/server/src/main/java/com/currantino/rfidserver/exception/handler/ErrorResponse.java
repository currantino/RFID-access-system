package com.currantino.rfidserver.exception.handler;

import lombok.Builder;

@Builder
public record ErrorResponse(String error, String message) {
}
