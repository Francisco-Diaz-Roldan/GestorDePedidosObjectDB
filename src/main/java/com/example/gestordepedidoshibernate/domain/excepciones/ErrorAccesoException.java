package com.example.gestordepedidoshibernate.domain.excepciones;

/**
 * Excepción personalizada para representar errores relacionados con el acceso, como credenciales incorrectas.
 */
public class ErrorAccesoException extends Exception {

    /**
     * Constructor que permite especificar un mensaje de error.
     *
     * @param msg Mensaje descriptivo del error.
     */
    public ErrorAccesoException(String msg) {
        super(msg);
    }
}
