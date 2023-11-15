package com.example.gestordepedidoshibernate.domain.sesion;

import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import com.example.gestordepedidoshibernate.domain.producto.Producto;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Sesion {
    @Getter
    @Setter
    private static Integer posicion = null;

    @Getter
    @Setter
    private static Usuario usuario;

    @Getter
    @Setter
    private static Pedido pedido;

    @Getter
    @Setter
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    @Getter
    @Setter
    private static ArrayList<Producto> productos = new ArrayList<>();

    @Getter
    @Setter
    private static ArrayList<Item> items = new ArrayList<>();

}
