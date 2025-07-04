package br.com.fiap.msfilaatendimento.infrastructure.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public record CustomHandlerController() {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
