package com.example.ilia.controller.exceptions;

import com.example.ilia.entity.Mensagem;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Mensagem> handleForbidden(BusinessException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Mensagem(bodyOfResponse));
    }

    @ExceptionHandler(value = {ConflictException.class})
    protected ResponseEntity<Mensagem> handleConflict(ConflictException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensagem(bodyOfResponse));
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<Mensagem> handleNotFound(NoSuchElementException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensagem(bodyOfResponse));
    }

    @ExceptionHandler(value = {InputException.class})
    protected ResponseEntity<Mensagem> handleBadRequest(InputException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensagem(bodyOfResponse));
    }
}
