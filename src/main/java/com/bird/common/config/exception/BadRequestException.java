package com.bird.common.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException(ErrorReasonCode reason) {
        super(HttpStatus.BAD_REQUEST);
    }
}
