package com.example.gestordepedidoshibernate.domain.pedido;

import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;           // Identificador único del pedido.

    @Column(name="fecha")
    private String fecha;                // Fecha de emisión del pedido.

    @Column(name = "total")
    private Double total;               // Total del pedido.

    @Column(name = "codigo_pedido") //La columna de referencia es la columna de la otra tabla de lo que quiero enlazar
    private String codigo_pedido;        // Código único asociado al pedido.

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;             // Identificador del usuario que realizó el pedido.

    @OneToMany(mappedBy = "codigo_pedido", fetch = FetchType.EAGER)
    private ArrayList<Item> items = new ArrayList<>();        // Lista de ítems incluidos en el pedido.


    @Override
    public String toString() {
        return "Pedido{" +
                "id del pedido: " + id_pedido +
                ", código del pedido: '" + codigo_pedido + '\'' +
                ", fecha de emisión del pedido: '" + fecha + '\'' +
                ", ID del usuario: " + usuario.getId_usuario() +
                ", total: " + total +
                ", ítems: " + items +
                '}';
    }
}
