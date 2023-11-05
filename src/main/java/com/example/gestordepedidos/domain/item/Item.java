package com.example.gestordepedidos.domain.item;

import com.example.gestordepedidos.domain.producto.Producto;

import java.io.Serializable;

public class Item {
    private Integer id_item;
    private  String codigo_pedido;
    private Integer cantidad;
    private Producto producto;

    public Item(Integer id_item, String codigo_pedido, Integer cantidad, Producto producto) {
        this.id_item = id_item;
        this.codigo_pedido = codigo_pedido;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Item() {
    }

    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    public String getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(String codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

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
