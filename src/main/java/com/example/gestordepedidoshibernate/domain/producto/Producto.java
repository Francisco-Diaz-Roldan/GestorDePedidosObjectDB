package com.example.gestordepedidoshibernate.domain.producto;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity@Table(name = "producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;
    @Column(name="nombre")
    private String nombre;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "cantidad_disponible")
    private Integer cantidad_disponible;

    @Override
    public String toString() {
        return "Producto{" +
                "id_producto: " + id_producto +
                ", nombre: '" + nombre + '\'' +
                ", precio: " + precio +
                ", cantidad_disponible=" + cantidad_disponible +
                '}';
    }
}
