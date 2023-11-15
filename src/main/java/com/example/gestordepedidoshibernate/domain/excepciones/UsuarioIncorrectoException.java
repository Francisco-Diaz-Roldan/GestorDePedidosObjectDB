package com.example.gestordepedidoshibernate.domain.excepciones;

/**
 * Excepción personalizada lanzada cuando se detecta un usuario incorrecto durante un proceso de autenticación.
 */
public class UsuarioIncorrectoException extends Exception {
    /**
     * Constructor de la excepción que recibe un mensaje de error personalizado.
     *
     * @param usuarioIncorrecto Hace referencia al mensaje que describe la razón de la excepción (usuario incorrecto).
     */
    public  UsuarioIncorrectoException(String usuarioIncorrecto){
        super(usuarioIncorrecto);
    }
}
