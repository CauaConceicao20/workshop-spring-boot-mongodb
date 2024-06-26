package com.cauaconceicao.workshopmongo.resources.exception;

import com.cauaconceicao.workshopmongo.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), httpStatus.value(), "Não encontrado", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }
}
