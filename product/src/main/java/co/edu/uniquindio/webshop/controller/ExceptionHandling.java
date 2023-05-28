package co.edu.uniquindio.webshop.controller;

import co.edu.uniquindio.webshop.dto.Response;
import co.edu.uniquindio.webshop.service.excepciones.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> catchException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new Response<>(e.getMessage()) );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response<String>> catchProductNotFoundException(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Response<>(e.getMessage()) );
    }

}
