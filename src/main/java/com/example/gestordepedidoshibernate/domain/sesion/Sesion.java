package com.example.gestordepedidoshibernate.domain.sesion;

import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import com.example.gestordepedidoshibernate.domain.producto.Producto;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Clase estática para almacenar información de sesión.
 * Contiene variables estáticas para el usuario actual, el pedido actual, el ítem actual, el producto actual,
 * y listas de pedidos, productos e ítems.
 */
public class Sesion {

    /** Posición del ítem en la sesión. */
    @Getter
    @Setter
    private static Integer posicion = null;

    /** Usuario actual en la sesión. */
    @Getter
    @Setter
    private static Usuario usuario;

    /** Pedido actual en la sesión. */
    @Getter
    @Setter
    private static Pedido pedido;

    /** Ítem actual en la sesión. */
    @Getter
    @Setter
    private static Item item;

    /** Producto actual en la sesión. */
    @Getter
    @Setter
    private static Producto producto;

    /** Lista de pedidos en la sesión. */
    @Getter
    @Setter
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    /** Lista de productos en la sesión. */
    @Getter
    @Setter
    private static ArrayList<Producto> productos = new ArrayList<>();

    /** Lista de ítems en la sesión. */
    @Getter
    @Setter
    private static ArrayList<Item> items = new ArrayList<>();
}
