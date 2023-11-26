package com.example.gestordepedidoshibernate.domain.item;

import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import com.example.gestordepedidoshibernate.domain.producto.Producto;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase de entidad que representa un item en el sistema, con anotaciones JPA para mapear la entidad a la base de datos.
 *
 * <p>Esta clase utiliza la anotación {@code @Data} de Lombok para generar automáticamente métodos como {@code toString()},
 * {@code hashCode()}, y {@code equals()}.</p>
 *
 * <p>Las anotaciones {@code @Entity} y {@code @Table} se utilizan para indicar que esta clase es una entidad JPA y
 * para especificar el nombre de la tabla en la base de datos, respectivamente.</p>
 */
@Data
@Entity
@Table(name = "item")
public class Item implements Serializable {

    /**
     * Identificador único del item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_item;

    /**
     * Código único del pedido al que pertenece.
     */
    @ManyToOne
    @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo_pedido")
    private Pedido codigo_pedido;

    /**
     * Cantidad de productos en este item.
     */
    @Column(name = "cantidad")
    private Integer cantidad;

    /**
     * Producto asociado a este item.
     */
    @OneToOne
    @JoinColumn(name = "producto")
    private Producto producto;

    /**
     * Genera una representación en cadena del objeto Item.
     *
     * @return Una cadena que representa el objeto Item.
     */
    @Override
    public String toString() {
        return "Item{" +
                "ID: " + id_item +
                ", código del pedido: '" + codigo_pedido.getCodigo_pedido() + '\'' +
                ", cantidad: " + cantidad +
                ", producto: " + producto +
                '}';
    }
}