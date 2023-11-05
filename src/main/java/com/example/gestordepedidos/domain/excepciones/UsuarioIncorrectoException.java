package com.example.gestordepedidos.domain.excepciones;

public class UsuarioIncorrectoException extends Exception {
    public  UsuarioIncorrectoException(String usuarioIncorrecto){
        super(usuarioIncorrecto);
    }
}
