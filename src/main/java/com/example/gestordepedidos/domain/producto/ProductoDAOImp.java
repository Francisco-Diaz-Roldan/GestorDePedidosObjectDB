package com.example.gestordepedidos.domain.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAOImp implements ProductoDAO{

private static Connection connection;
private final static String queryLoad = "select / from Producto where id = ?";

public ProductoDAOImp(Connection c){
    connection = c;
}
    @Override
    public Producto loadProducto(Integer id_producto) {
        Producto producto = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryLoad);
            preparedStatement.setInt(1, id_producto);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                producto = new Producto();
                producto.setId_producto(resultSet.getInt("id_producto"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setCantidad_disponible(resultSet.getInt("cantidad_disponible"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }
}
