package com.example.gestordepedidoshibernate.domain.sesion;

import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import com.example.gestordepedidoshibernate.domain.producto.Producto;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;

import java.util.ArrayList;
/**
 * Clase que almacena información de la sesión actual del usuario.
 * Esta clase proporciona acceso a datos como el usuario actual, los pedidos, productos e items asociados a la sesión.
 */
public class Sesion {
    private static Integer posicion = null;
    private static Usuario usuario;
    private static Pedido pedido;
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();

    /**
     * Obtiene la posición actual en la lista de pedidos.
     *
     * @return La posición actual en la lista de pedidos.
     */
    public static Integer getPosicion() {
        return posicion;
    }

    /**
     * Establece la posición actual en la lista de pedidos.
     *
     * @param posicion La posición actual en la lista de pedidos.
     */
    public static void setPosicion(Integer posicion) {
        Sesion.posicion = posicion;
    }

    /**
     * Obtiene el usuario actual de la sesión.
     *
     * @return El usuario actual de la sesión.
     */
    public static Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario actual de la sesión.
     *
     * @param usuario El usuario actual de la sesión.
     */
    public static void setUsuario(Usuario usuario) {
        Sesion.usuario = usuario;
    }

    /**
     * Obtiene el pedido actual de la sesión.
     *
     * @return El pedido actual de la sesión.
     */
    public static Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el pedido actual de la sesión.
     *
     * @param pedido El pedido actual de la sesión.
     */
    public static void setPedido(Pedido pedido) {
        Sesion.pedido = pedido;
    }

    /**
     * Obtiene la lista de pedidos asociados a la sesión.
     *
     * @return La lista de pedidos asociados a la sesión.
     */
    public static ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Establece la lista de pedidos asociados a la sesión.
     *
     * @param pedidos La lista de pedidos asociados a la sesión.
     */
    public static void setPedidos(ArrayList<Pedido> pedidos) {
        Sesion.pedidos = pedidos;
    }

    /**
     * Obtiene la lista de productos asociados a la sesión.
     *
     * @return La lista de productos asociados a la sesión.
     */
    public static ArrayList<Producto> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos asociados a la sesión.
     *
     * @param productos La lista de productos asociados a la sesión.
     */
    public static void setProductos(ArrayList<Producto> productos) {
        Sesion.productos = productos;
    }

    /**
     * Obtiene la lista de items asociados a la sesión.
     *
     * @return La lista de items asociados a la sesión.
     */
    public static ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Establece la lista de items asociados a la sesión.
     *
     * @param items La lista de items asociados a la sesión.
     */
    public static void setItems(ArrayList<Item> items) {
        Sesion.items = items;
    }
}
