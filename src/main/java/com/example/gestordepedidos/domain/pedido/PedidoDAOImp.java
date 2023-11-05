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

public class PedidoDAOImp implements PedidoDAO{

    private static Connection connection;

    private final static String queryLoadAll = "select * from Pedido where usuario = ?";

    public PedidoDAOImp(Connection c){connection = c;}
    @Override
    public ArrayList<Pedido> loadAll(Integer id_pedido) {
        ArrayList<Pedido> salida = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryLoadAll);
            ItemDAOImp itemDAOImp = new ItemDAOImp(DBConnection.getConnection());
            preparedStatement.setInt(1, id_pedido);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
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
