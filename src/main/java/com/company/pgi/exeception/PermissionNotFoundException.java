package com.company.pgi.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PermissionNotFoundException extends RuntimeException {

    public PermissionNotFoundException() {
        super( "Usuário não tem permisssão para executar a ação." );
    }
    
    public PermissionNotFoundException(String message){ 
        super(message);
    }
}

