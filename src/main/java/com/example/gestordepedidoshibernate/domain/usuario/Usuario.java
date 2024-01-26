package com.example.gestordepedidoshibernate.domain.usuario;

import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario.
 * La anotación @Data de Lombok genera automáticamente los métodos getter, setter, toString, equals y hashCode.
 */

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    /** Id único del usuario. */

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

    public Usuario(String nombre, String pass, String email, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pass = pass;
        this.email = email;
        this.pedidos = pedidos;
    }

    /** Lista de pedidos asociados al usuario. */

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>(0);

}
