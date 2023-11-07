package com.example.gestordepedidos.domain.usuario;

import com.example.gestordepedidos.domain.excepciones.PasswordIncorrectaException;
import com.example.gestordepedidos.domain.excepciones.UsuarioIncorrectoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación de la interfaz UsuarioDAO para acceder y gestionar datos de usuarios en una base de datos.
 */
public class UsuarioDAOImp implements UsuarioDAO {

    private static Connection connection;
    private static String loadUser = "select * from usuario where email = ?";

    /**
     * Constructor de la clase UsuarioDAOImp.
     *
     * @param c La conexión a la base de datos que se utilizará para acceder a los datos de usuario.
     */
    public UsuarioDAOImp(Connection c) {
        connection = c;
    }

    /**
     * Carga un usuario basado en la dirección de correo electrónico y la contraseña proporcionados.
     *
     * @param email    La dirección de correo electrónico del usuario que se desea cargar.
     * @param password La contraseña asociada al usuario.
     * @return El objeto Usuario cargado si los datos son válidos.
     * @throws PasswordIncorrectaException    Si la contraseña proporcionada no coincide con la del usuario.
     * @throws UsuarioIncorrectoException     Si no se encuentra un usuario con la dirección de correo electrónico dada.
     */
    @Override
    public Usuario loadUser(String email, String password) throws PasswordIncorrectaException, UsuarioIncorrectoException {
        Usuario usuario;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(loadUser);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario(email, password);
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPass(resultSet.getString("pass"));
                usuario.setId_usuario(resultSet.getInt("id_usuario"));
                usuario.setNombre(resultSet.getString("nombre"));
                if (!password.equals(usuario.getPass())) {
                    throw new PasswordIncorrectaException("Contraseña incorrecta");
                }
            } else {
                throw new UsuarioIncorrectoException("Usuario incorrecto");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}