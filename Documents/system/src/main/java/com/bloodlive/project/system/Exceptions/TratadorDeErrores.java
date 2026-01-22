package com.bloodlive.project.system.Exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores {


    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<Map<String, String>> manejarValidacion(ValidacionException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)   // o CONFLICT
                .body(Map.of("error", ex.getMessage()));
    }


    @ExceptionHandler(ValidacionExceptionToken.class)
    public ResponseEntity<Map<String, String>> manejarValidacionjwt(ValidacionExceptionToken ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)   // o CONFLICT
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        // Extraer solo los mensajes de error de los campos
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleParseErrors(HttpMessageNotReadableException ex) {

        Map<String, String> error = new HashMap<>();
        Throwable causa = ex.getCause();

        if(causa instanceof InvalidFormatException invalid ){

            String campo = invalid.getPath().get(0).getFieldName();

            String valorInvalido = invalid.getValue().getClass().getSimpleName();

            String tipoEsperado = invalid.getTargetType().getSimpleName();

            String mensaje = "Tipo de valor invalido "+ valorInvalido+". Valor esperado: " + tipoEsperado ;

            error.put(campo, mensaje);

        }
        else{
            error.put("error","BAD REQUEST");
        }


        return ResponseEntity.badRequest().body(error);
    }



}



