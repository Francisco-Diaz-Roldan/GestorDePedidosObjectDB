package com.example.gestordepedidoshibernate.domain.pedido;

import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la entidad Pedido, la cual se mapea a la tabla "pedido" en la base de datos.
 */
@Data
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    /**
     * Identificador único del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;

    /**
     * Fecha de emisión del pedido.
     */
    @Column(name = "fecha")
    private String fecha;

    /**
     * Total del pedido.
     */
    @Column(name = "total")
    private Double total;

    /**
     * Código único asociado al pedido.
     */
    @Column(name = "codigo_pedido")
    private String codigo_pedido;

    /**
     * Usuario que realizó el pedido.
     */
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    /**
     * Lista de ítems incluidos en el pedido.
     */
    @OneToMany(mappedBy = "codigo_pedido", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    /**
     * Devuelve una representación en cadena del objeto Pedido.
     *
     * @return Una cadena que representa el objeto Pedido.
     */
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
