package com.example.gestordepedidos.domain.usuario;

import com.example.gestordepedidos.domain.excepciones.PasswordIncorrectaException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
/**
 * Interfaz que define métodos para acceder y gestionar datos de usuarios en una base de datos.
 */
public interface UsuarioDAO {
    /**
     * Carga un usuario basado en la dirección de correo electrónico y la contraseña proporcionados.
     *
     * @param email    Se refiere a la dirección de correo electrónico del usuario que se desea cargar.
     * @param password Se refiere a la contraseña asociada al usuario.
     * @return Se refiere al objeto Usuario cargado si los datos son válidos.
     * @throws UserPrincipalNotFoundException Si no se encuentra un usuario con la dirección de correo electrónico dada.
     * @throws PasswordIncorrectaException    Si la contraseña proporcionada no coincide con la del usuario.
     */
    public Usuario loadUser(String email, String password) throws UserPrincipalNotFoundException, PasswordIncorrectaException;
}
