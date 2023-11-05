package com.example.gestordepedidos.domain.usuario;

import com.example.gestordepedidos.domain.excepciones.PasswordIncorrectaException;
import com.example.gestordepedidos.domain.excepciones.UsuarioIncorrectoException;
import com.example.gestordepedidos.domain.pedido.Pedido;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private Integer id_usuario;
    private String nombre;
    private String pass;
    private String email;
    private ArrayList<Pedido> pedido;

    public Usuario (Integer id_usuario, String nombre, String password, String email){
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.pass = password;
        this.email = email;
    }


    public Usuario(String email, String password) throws UsuarioIncorrectoException, PasswordIncorrectaException {
        this.pedido = new ArrayList<Pedido>();
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id: " + id_usuario +
                ", nombre: '" + nombre + '\'' +
                ", contraseña: '" + pass + '\'' +
                ", email: '" + email + '\'' +
                '}';//TODO añadir pedido
    }
}




