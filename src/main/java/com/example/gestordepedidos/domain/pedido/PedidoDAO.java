package com.example.gestordepedidos.domain.pedido;

import java.util.ArrayList;

public interface PedidoDAO {
    public ArrayList<Pedido> loadAll(Integer id_pedido);
}

