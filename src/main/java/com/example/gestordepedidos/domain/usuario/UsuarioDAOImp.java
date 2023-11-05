package com.example.gestordepedidos.domain.usuario;

import com.example.gestordepedidos.domain.excepciones.PasswordIncorrectaException;
import com.example.gestordepedidos.domain.excepciones.UsuarioIncorrectoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOImp implements UsuarioDAO{
    private static Connection connection;
    private static String loadUser = "select * from usuario where email = ?";
    public UsuarioDAOImp (Connection c){ connection = c;}
    @Override
    public Usuario loadUser(String email, String password) throws PasswordIncorrectaException {
        Usuario usuario;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(loadUser);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                usuario = new Usuario(email, password);
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPass(resultSet.getString("pass"));
                usuario.setId_usuario(resultSet.getInt("id_usuario"));
                if (!password.equals(usuario.getPass())){
                    throw new PasswordIncorrectaException("Contraseña incorrecta");
                }
                usuario.setNombre(resultSet.getString("nombre"));
            } else {
                throw new UsuarioIncorrectoException("Usuario incorrecto");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
