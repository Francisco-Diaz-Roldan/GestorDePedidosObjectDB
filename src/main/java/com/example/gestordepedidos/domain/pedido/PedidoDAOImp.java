package com.example.gestordepedidos.domain.pedido;

import com.example.gestordepedidos.domain.conexionbbdd.DBConnection;
import com.example.gestordepedidos.domain.item.ItemDAOImp;
import com.example.gestordepedidos.domain.sesion.Sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * Implementación de la interfaz PedidoDAO que proporciona métodos para acceder a datos de pedidos en una base de datos.
 */
public class PedidoDAOImp implements PedidoDAO {

    private static Connection connection;
    private final static String queryLoadAll = "select * from Pedido where usuario = ?";

    /**
     * Constructor de la clase que recibe una conexión a la base de datos.
     *
     * @param c La conexión a la base de datos.
     */
    public PedidoDAOImp(Connection c) {
        connection = c;
    }

    /**
     * Carga todos los pedidos asociados a un usuario con el ID especificado.
     *
     * @param id_pedido El ID del usuario del que se desean cargar los pedidos.
     * @return Una lista de objetos Pedido que representan los pedidos asociados al usuario.
     */
    @Override
    public ArrayList<Pedido> loadAll(Integer id_pedido) {
        ArrayList<Pedido> salida = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryLoadAll);
            ItemDAOImp itemDAOImp = new ItemDAOImp(DBConnection.getConnection());
            preparedStatement.setInt(1, id_pedido);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(resultSet.getInt("id_pedido"));
                pedido.setCodigo_pedido(resultSet.getString("codigo_pedido"));
                pedido.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("fecha")));
                pedido.setUsuario(resultSet.getInt("usuario"));
                pedido.setTotal(resultSet.getInt("total"));
                pedido.setItem(itemDAOImp.loadAll(pedido.getCodigo_pedido()));
                Sesion.setPedido(pedido);
                salida.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }
}
