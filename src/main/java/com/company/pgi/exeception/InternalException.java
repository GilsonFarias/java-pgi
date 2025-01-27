package com.company.pgi.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class InternalException extends RuntimeException {
    public InternalException(){
        super("Exceção interna.");
    }

    public InternalException(String message){
        super(message);
    }

}
