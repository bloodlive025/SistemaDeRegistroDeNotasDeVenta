package com.bloodlive.project.system.Exceptions;

public class ValidacionExceptionToken extends RuntimeException {
    public ValidacionExceptionToken() {
        super("El token ingresado no es el correcto");
    }
}
