package com.example.gestordepedidos.domain.item;

import com.example.gestordepedidos.domain.producto.Producto;

import java.io.Serializable;
/**
 * Clase que representa un elemento item dentro de un pedido, que incluye información sobre el producto, la cantidad
 * y el pedido al que pertenece.
 */
public class Item {
    private Integer id_item;           // Identificador único del item.
    private  String codigo_pedido;     // Código único del pedido al que pertenece.
    private Integer cantidad;          // Cantidad de productos en este item.
    private Producto producto;        // Producto asociado a este item.

    /**
     * Constructor de la clase Item para inicializar sus atributos.
     *
     * @param id_item Hace referencia al identificador único del item.
     * @param codigo_pedido Se refiere al código único del pedido al que pertenece.
     * @param cantidad Se refiere a la cantidad de productos en este item.
     * @param producto Se refiere al producto asociado a este item.
     */
    public Item(Integer id_item, String codigo_pedido, Integer cantidad, Producto producto) {
        this.id_item = id_item;
        this.codigo_pedido = codigo_pedido;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    /**
     * Constructor por defecto de la clase Item.
     */
    public Item() {
    }

    /**
     * Obtiene el identificador único del item.
     *
     * @return El identificador único del item.
     */
    public Integer getId_item() {
        return id_item;
    }

    /**
     * Establece el identificador único del item.
     *
     * @param id_item El identificador único del item.
     */
    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    /**
     * Obtiene el código único del pedido al que pertenece el item.
     *
     * @return El código único del pedido.
     */
    public String getCodigo_pedido() {
        return codigo_pedido;
    }

    /**
     * Establece el código único del pedido al que pertenece el item.
     *
     * @param codigo_pedido El código único del pedido.
     */
    public void setCodigo_pedido(String codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    /**
     * Obtiene la cantidad de productos en este item.
     *
     * @return La cantidad de productos en el item.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de productos en este item.
     *
     * @param cantidad La cantidad de productos en el item.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado a este item.
     *
     * @return El producto asociado al item.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado a este item.
     *
     * @param producto El producto asociado al item.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Devuelve una representación en cadena de la información del item.
     *
     * @return Una cadena que representa el item con su ID, código de pedido, cantidad y producto.
     */
    @Override
    public String toString() {
        return "Item{" +
                "ID: " + id_item +
                ", codigo del pedido: '" + codigo_pedido + '\'' +
                ", cantidad: " + cantidad +
                ", producto: " + producto +
                '}';
    }
}
