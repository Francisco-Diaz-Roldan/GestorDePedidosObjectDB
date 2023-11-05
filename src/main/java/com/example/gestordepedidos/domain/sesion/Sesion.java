package com.example.gestordepedidos.domain.sesion;

import com.example.gestordepedidos.domain.item.Item;
import com.example.gestordepedidos.domain.pedido.Pedido;
import com.example.gestordepedidos.domain.producto.Producto;
import com.example.gestordepedidos.domain.usuario.Usuario;

import java.util.ArrayList;

public class Sesion {
    private static Integer posicion = null;
    private static Usuario usuario;
    private static Pedido pedido;
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();

    public static Integer getPosicion() {return posicion;}

    public static void setPosicion(Integer posicion) {
        Sesion.posicion = posicion;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Sesion.usuario = usuario;
    }

    public static Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        Sesion.pedido = pedido;
    }

    public static ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public static void setPedidos(ArrayList<Pedido> pedidos) {
        Sesion.pedidos = pedidos;
    }

    public static ArrayList<Producto> getProductos() {
        return productos;
    }

    public static void setProductos(ArrayList<Producto> productos) {
        Sesion.productos = productos;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> items) {
        Sesion.items = items;
    }
}
