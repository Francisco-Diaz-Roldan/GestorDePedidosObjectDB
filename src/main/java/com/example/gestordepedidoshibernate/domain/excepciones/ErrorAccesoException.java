package com.example.gestordepedidoshibernate.domain.excepciones;
/**
 * Excepción personalizada lanzada cuando se detecta una contraseña incorrecta durante un proceso de autenticación.
 */
public class ErrorAccesoException extends Exception {
    /**
     * Constructor de la excepción que recibe un mensaje de error personalizado.
     *
     * @param msg Se refiere al mensaje que describe la razón de la excepción (contraseña incorrecta).
     */
    public ErrorAccesoException(String msg) {
        super(msg);
    }
}
