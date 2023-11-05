package com.example.gestordepedidos.domain.item;

import java.util.ArrayList;

/**
 * Interfaz para acceder a los datos de los elementos (items) de un pedido en una base de datos.
 */
public interface ItemDAO {

    /**
     * Carga todos los elementos de un pedido identificados por su código de pedido.
     *
     * @param codigo_pedido El código único del pedido del cual cargar los elementos.
     * @return Una lista de elementos (items) pertenecientes al pedido especificado.
     */
    public ArrayList<Item> loadAll(String codigo_pedido);
}
