package com.example.gestordepedidos.domain.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Implementación de la interfaz ProductoDAO que permite acceder a los datos de productos desde una base de datos.
 */
public class ProductoDAOImp implements ProductoDAO{

    private static Connection connection;
    ///private final static String queryLoad = "select * from Producto where id = ?";
    private final static String queryLoad = "select * from Producto where id_producto = ?";

    /**
     * Constructor de la clase ProductoDAOImp.
     *
     * @param c Conexión a la base de datos.
     */
    public ProductoDAOImp(Connection c){
        connection = c;
    }

    /**
     * Carga un producto por su ID desde la base de datos.
     *
     * @param id_producto El ID del producto que se desea cargar.
     * @return El objeto Producto correspondiente al ID especificado, o null si no se encuentra.
     */
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
