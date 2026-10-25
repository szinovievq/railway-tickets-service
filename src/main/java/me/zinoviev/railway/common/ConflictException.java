package me.zinoviev.railway.common;

import org.springframework.http.HttpStatus;

public class ConflictException extends BusinessException{
    protected ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
