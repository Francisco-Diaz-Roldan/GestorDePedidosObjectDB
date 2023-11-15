package com.example.gestordepedidoshibernate.domain.pedido;

import java.util.ArrayList;
/**
 * Interfaz que define las operaciones para acceder a datos de pedidos en una base de datos.
 */
public interface PedidoDAO {
    /**
     * Carga todos los pedidos asociados a un usuario con el ID especificado.
     *
     * @param id_pedido El ID del usuario del que se desean cargar los pedidos.
     * @return Una lista de objetos Pedido que representan los pedidos asociados al usuario.
     */
    public ArrayList<Pedido> loadAll(Integer id_pedido);
}
