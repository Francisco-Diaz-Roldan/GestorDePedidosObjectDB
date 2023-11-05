package com.example.gestordepedidos.domain.excepciones;

public class PasswordIncorrectaException extends Exception{
    public  PasswordIncorrectaException(String passwordIncorrecta){
        super(passwordIncorrecta);
    }
}
