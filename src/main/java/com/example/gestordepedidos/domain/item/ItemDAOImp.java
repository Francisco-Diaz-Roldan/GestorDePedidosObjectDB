package com.example.gestordepedidos.domain.item;

import com.example.gestordepedidos.domain.conexionbbdd.DBConnection;
import com.example.gestordepedidos.domain.producto.Producto;
import com.example.gestordepedidos.domain.producto.ProductoDAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImp implements ItemDAO{
    private static Connection connection;
    private final static String queryLoadAll = "select * from Item where codigo_pedido = ?";

    public ItemDAOImp(Connection c){
        connection = c;
    }

    @Override
    public ArrayList<Item> loadAll(String codigo_pedido) {
        ArrayList<Item> salida = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryLoadAll);
            preparedStatement.setString(1,codigo_pedido);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Item item = new Item();
                item.setId_item(resultSet.getInt("id_item"));
                item.setCodigo_pedido(resultSet.getString("codigo_pedido"));
                item.setCantidad(resultSet.getInt("cantidad"));
                Integer id_producto = resultSet.getInt("producto");
                ProductoDAOImp productoDAOImp = new ProductoDAOImp(DBConnection.getConnection());
                Producto producto = productoDAOImp.loadProducto(id_producto);
                item.setProducto(producto);
                salida.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

}
