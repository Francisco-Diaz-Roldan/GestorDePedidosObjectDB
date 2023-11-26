package com.example.gestordepedidoshibernate.domain.item;

import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import com.example.gestordepedidoshibernate.domain.producto.Producto;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_item;           // Identificador único del item.
    @ManyToOne
    @JoinColumn(name = "codigo_pedido", referencedColumnName="codigo_pedido")
    private Pedido codigo_pedido;     // Código único del pedido al que pertenece.

    @Column(name = "cantidad")
    private Integer cantidad;          // Cantidad de productos en este item.

    @OneToOne
    @JoinColumn(name = "producto")
    private Producto producto;        // Producto asociado a este item.



    @Override
    public String toString() {
        return "Item{" +
                "ID: " + id_item +
                ", codigo del pedido: '" + codigo_pedido.getCodigo_pedido() + '\'' +
                ", cantidad: " + cantidad +
                ", producto: " + producto +
                '}';
    }
}
