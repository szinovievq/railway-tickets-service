package me.zinoviev.railway.common;

import org.springframework.http.HttpStatus;

public class GoneException extends BusinessException{
    protected GoneException(String message) {
        super(HttpStatus.GONE, message);
    }
}
