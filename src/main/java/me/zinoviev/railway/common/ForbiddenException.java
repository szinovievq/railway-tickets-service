package me.zinoviev.railway.common;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BusinessException{
    protected ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
