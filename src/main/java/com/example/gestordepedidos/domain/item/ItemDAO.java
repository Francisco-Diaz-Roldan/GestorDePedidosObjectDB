package com.example.gestordepedidos.domain.item;

import java.util.ArrayList;

public interface ItemDAO {
public ArrayList<Item> loadAll(String codigo_pedido);
}
