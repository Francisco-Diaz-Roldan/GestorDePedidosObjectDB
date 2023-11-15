package com.example.gestordepedidoshibernate.domain.excepciones;
/**
 * Excepción personalizada lanzada cuando se detecta una contraseña incorrecta durante un proceso de autenticación.
 */
public class PasswordIncorrectaException extends Exception {
    /**
     * Constructor de la excepción que recibe un mensaje de error personalizado.
     *
     * @param passwordIncorrecta Se refiere al mensaje que describe la razón de la excepción (contraseña incorrecta).
     */
    public PasswordIncorrectaException(String passwordIncorrecta) {
        super(passwordIncorrecta);
    }
}
