package com.company.pgi.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.company.pgi.model.ApiException;

@ControllerAdvice
public class PgiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalException.class)
    private ResponseEntity<ApiException> InternalNotFoundHendler(InternalException exception) {
        // Obtém o código de status HTTP da anotação @ResponseStatus
        HttpStatus status = exception.getClass().isAnnotationPresent(ResponseStatus.class)
        ? exception.getClass().getAnnotation(ResponseStatus.class).value()
        : HttpStatus.INTERNAL_SERVER_ERROR;
        
        // Cria o objeto ApiException com a mensagem da exceção
        ApiException apiException = new ApiException(exception.getMessage());
        
        // Retorna a ResponseEntity diretamente
        return ResponseEntity.status(status).body(apiException);
    }
    
    @ExceptionHandler(PermissionNotFoundException.class)
    private ResponseEntity<ApiException> permissionNotFoundHendler(PermissionNotFoundException exception) {
        HttpStatus status = exception.getClass().isAnnotationPresent(ResponseStatus.class)
        ? exception.getClass().getAnnotation(ResponseStatus.class).value()
        : HttpStatus.INTERNAL_SERVER_ERROR;

        ApiException apiException = new ApiException(exception.getMessage());

        return ResponseEntity.status(status).body(apiException);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    private ResponseEntity<String> personNotFoundHendler(PersonNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person não encontrado");
    }

}
