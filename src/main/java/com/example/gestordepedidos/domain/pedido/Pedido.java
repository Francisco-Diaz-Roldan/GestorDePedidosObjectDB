package com.example.gestordepedidos.domain.pedido;

import com.example.gestordepedidos.domain.item.Item;

import java.util.ArrayList;

public class Pedido {
    private Integer id_pedido;
    private String codigo_pedido;
    private String fecha;
    private Integer usuario;
    private Integer total;
    private ArrayList<Item>item = new ArrayList<>();

    public Pedido(Integer id_pedido, String codigo_pedido, String fecha, Integer usuario, Integer total, ArrayList<Item> item) {
        this.id_pedido = id_pedido;
        this.codigo_pedido = codigo_pedido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.total = total;
        this.item = item;
    }

    public Pedido() {

    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(String codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id del pedido: " + id_pedido +
                ", codigo del pedido: '" + codigo_pedido + '\'' +
                ", fecha de emisión del pedido: '" + fecha + '\'' +
                ", usuario: " + usuario +
                ", total: " + total +
                ", item: " + item +
                '}';
    }
}
