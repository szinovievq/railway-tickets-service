package me.zinoviev.railway.common;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException{

    public NotFoundException(String s){
        super(HttpStatus.NOT_FOUND, s + " not found");
    }
}
