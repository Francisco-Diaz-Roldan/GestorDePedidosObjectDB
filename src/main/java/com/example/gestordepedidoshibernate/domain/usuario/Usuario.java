package com.example.gestordepedidoshibernate.domain.usuario;

import com.example.gestordepedidoshibernate.domain.excepciones.ErrorAccesoException;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(name="nombre")
    private String nombre;

    @Column(name = "pass")
    private String pass;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "usuario", fetch =  FetchType.EAGER)
    private ArrayList<Pedido> pedidos = new ArrayList<>(0);

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
