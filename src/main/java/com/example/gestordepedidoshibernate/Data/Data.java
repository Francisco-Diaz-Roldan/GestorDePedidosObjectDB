package com.example.gestordepedidoshibernate.Data;

import com.example.gestordepedidoshibernate.domain.producto.Producto;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Producto> generarProductos(){
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Smartphone", 299.00, 50));
        listaProductos.add(new Producto("Portátil", 799.00, 30));
        listaProductos.add(new Producto("Auriculares", 79.00, 100));
        listaProductos.add(new Producto("Televisor", 599.00, 20));
        listaProductos.add(new Producto("Tablet", 199.00, 40));
        return listaProductos;
    }

    public static List<Usuario> generarUsuarios(){
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("usuario1","1234","usuario1@gmail.com",new ArrayList<>()));
        listaUsuarios.add(new Usuario("usuario2","1234","usuario2@gmail.com",new ArrayList<>()));
        listaUsuarios.add(new Usuario("usuario3","1234","usuario3@gmail.com",new ArrayList<>()));
        listaUsuarios.add(new Usuario("usuario4","1234","usuario4@gmail.com",new ArrayList<>()));
        return listaUsuarios;
    }
}

