package com.example.gestordepedidos.domain.usuario;

import com.example.gestordepedidos.domain.excepciones.PasswordIncorrectaException;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface UsuarioDAO {
    //Le paso los datos que quiero comprobar
    public Usuario loadUser(String email, String password) throws UserPrincipalNotFoundException, PasswordIncorrectaException;
}
