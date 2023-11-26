package com.example.gestordepedidoshibernate.domain.producto;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa la entidad Producto en la base de datos. Utiliza la anotación JPA `@Entity` para indicar que
 * está asociada con una tabla en la base de datos, y utiliza la anotación `@Table` para especificar el nombre de la tabla.
 */

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;  // Identificador único del producto.

    @Column(name = "nombre")
    private String nombre;        // Nombre del producto.

    @Column(name = "precio")
    private Double precio;        // Precio del producto.

    @Column(name = "cantidad_disponible")
    private Integer cantidad_disponible;  // Cantidad disponible del producto.

    /**
     * Devuelve una representación en cadena del objeto Producto, mostrando sus atributos.
     *
     * @return Cadena que representa el objeto Producto.
     */

    @Override
    public String toString() {
        return nombre ;
    }
}
