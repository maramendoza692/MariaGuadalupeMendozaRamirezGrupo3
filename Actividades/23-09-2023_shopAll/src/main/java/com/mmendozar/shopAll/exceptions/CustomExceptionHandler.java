package com.mmendozar.shopAll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mmendozar.shopAll.model.dto.RespuestaGenerica;

@ControllerAdvice
public class CustomExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus status =  HttpStatus.BAD_REQUEST;
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError.getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(status,errorMessage),status);
    }

    @ExceptionHandler(ProductosNoDisponiblesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleProductosNoDisponiblesException(ProductosNoDisponiblesException ex) {
        HttpStatus status =  HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(status,ex.getMessage()),status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<RespuestaGenerica> handleEntityNotFoundException(EntityNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RespuestaGenerica respuesta = new RespuestaGenerica();
        respuesta.setExito(false);
        respuesta.setCodigo(status.value());
        respuesta.setMensaje(ex.getMessage());
        return new ResponseEntity<>(respuesta,status);
    }

    @ExceptionHandler(ProductoSinStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<RespuestaGenerica> handleSinStock(ProductoSinStockException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RespuestaGenerica respuesta = new RespuestaGenerica();
        respuesta.setExito(false);
        respuesta.setCodigo(status.value());
        respuesta.setMensaje(ex.getMessage());
        return new ResponseEntity<>(respuesta,status);
    }
}
