package com.example.gestordepedidoshibernate.domain.usuario;

import com.example.gestordepedidoshibernate.domain.excepciones.PasswordIncorrectaException;
import com.example.gestordepedidoshibernate.domain.excepciones.UsuarioIncorrectoException;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Clase que representa a un usuario del sistema.
 * Un usuario tiene atributos como un identificador único, nombre, contraseña, dirección de correo electrónico y una lista de pedidos asociados.
 */
public class Usuario implements Serializable {
    private Integer id_usuario;
    private String nombre;
    private String pass;
    private String email;
    private ArrayList<Pedido> pedido;

    /**
     * Constructor para crear un objeto Usuario con atributos específicos.
     *
     * @param id_usuario El identificador único del usuario.
     * @param nombre El nombre del usuario.
     * @param password La contraseña del usuario.
     * @param email La dirección de correo electrónico del usuario.
     */
    public Usuario(Integer id_usuario, String nombre, String password, String email) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.pass = password;
        this.email = email;
    }

    /**
     * Constructor para crear un objeto Usuario con email y contraseña.
     * Este constructor se utiliza para la autenticación del usuario y no incluye la lista de pedidos.
     *
     * @param email    La dirección de correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @throws UsuarioIncorrectoException Si el usuario no es válido.
     * @throws PasswordIncorrectaException Si la contraseña es incorrecta.
     */
    public Usuario(String email, String password) throws UsuarioIncorrectoException, PasswordIncorrectaException {
        this.pedido = new ArrayList<Pedido>();
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public Integer getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id_usuario El identificador único del usuario.
     */
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param pass La contraseña del usuario.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Obtiene la dirección de correo electrónico del usuario.
     *
     * @return La dirección de correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la dirección de correo electrónico del usuario.
     *
     * @param email La dirección de correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Representación en formato de cadena de texto del objeto Usuario.
     *
     * @return Una cadena que contiene información sobre el usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id: " + id_usuario +
                ", nombre: '" + nombre + '\'' +
                ", contraseña: '" + pass + '\'' +
                ", email: '" + email + '\'' +
                '}';
    }
}
