package com.example.gestordepedidoshibernate.domain.usuario;

import com.example.gestordepedidoshibernate.domain.excepciones.ErrorAccesoException;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa a un usuario.
 * La anotación @Data de Lombok genera automáticamente los métodos getter, setter, toString, equals y hashCode.
 */

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    /** Identificador único del usuario. */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    /** Nombre del usuario. */

    @Column(name="nombre")
    private String nombre;

    /** Contraseña del usuario. */

    @Column(name = "pass")
    private String pass;

    /** Correo electrónico del usuario. */

    @Column(name = "email")
    private String email;

    /** Lista de pedidos asociados al usuario. */

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private ArrayList<Pedido> pedidos = new ArrayList<>(0);

    /**
     * Representación en cadena del objeto Usuario.
     * @return Cadena que representa al usuario.
     */

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario: " + id_usuario +
                ", nombre: '" + nombre + '\'' +
                ", pass: '" + pass + '\'' +
                ", email: '" + email + '\'' +
                ", pedidos: " + pedidos +
                '}';
    }
}
