package com.mediapp.mediapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
@Slf4j
public class ResponseExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected final ResponseEntity<ExceptionResponse> manejandoException(Exception ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        log.error(er.getMensaje());
        return new ResponseEntity<>(er,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModeloNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> manejarModelNotFoundException(ModeloNotFoundException ex, WebRequest request){

        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensaje = ex.getBindingResult().getAllErrors().stream()
                .map( e -> { return e.getDefaultMessage().concat(","); }
        ).collect(Collectors.joining());

        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(mensaje,HttpStatus.BAD_REQUEST);
    }

}
