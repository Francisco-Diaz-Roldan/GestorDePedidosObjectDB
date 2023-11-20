package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;
import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;

import com.example.gestordepedidoshibernate.domain.sesion.Sesion;
import com.example.gestordepedidoshibernate.domain.usuario.UsuarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la vista principal del usuario.
 * Esta clase maneja las interacciones de la vista principal del usuario, como la visualización de pedidos y la gestión
 * de eventos.
 */

public class UserViewController implements Initializable {
    @javafx.fxml.FXML
    private Label lblUsuario; // Etiqueta para mostrar el nombre del usuario actual.
    @javafx.fxml.FXML
    private TableView<Pedido> tPedidos; // Tabla para mostrar los pedidos del usuario.
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cIdPedido; // Columna para mostrar el ID de los pedidos.
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cCodigoPedido; // Columna para mostrar el código de los pedidos.
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cFecha; // Columna para mostrar la fecha de los pedidos.
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cUsuario; // Columna para mostrar el usuario relacionado con los pedidos.
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cTotal; // Columna para mostrar el total de los pedidos.

    private ObservableList<Pedido> observablePedidos; // Lista observable para almacenar y mostrar los pedidos.



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura las columnas de la tabla de pedidos y sus valores.
        cIdPedido.setCellValueFactory((fila) -> {
            String id_pedido = String.valueOf(fila.getValue().getId_pedido());
            return new SimpleStringProperty(id_pedido);
        });

        cCodigoPedido.setCellValueFactory((fila) -> {
            String codigo_pedido = String.valueOf(fila.getValue().getCodigo_pedido());
            return new SimpleStringProperty(codigo_pedido);
        });

        cFecha.setCellValueFactory((fila) -> {
            String fecha = String.valueOf(fila.getValue().getFecha());
            return new SimpleStringProperty(fecha);
        });

        cUsuario.setCellValueFactory((fila) -> {
            String usuario = String.valueOf(fila.getValue().getUsuario());
            return new SimpleStringProperty(usuario);
        });

        cTotal.setCellValueFactory((fila) -> {
            String total = String.valueOf(fila.getValue().getTotal());
            return new SimpleStringProperty(total);
        });

        // Muestro el nombre del usuario en una etiqueta de bienvenida.
        lblUsuario.setText("Bienvenido " + Sesion.getUsuario().getNombre());

        // Creo una lista observable para los pedidos y carga los pedidos del usuario actual.
        observablePedidos = FXCollections.observableArrayList();

        Sesion.setUsuario((new UsuarioDAO()).get(Sesion.getUsuario().getId_usuario()));

        cargarLista();

        observablePedidos.addAll(Sesion.getPedidos());

        
        // Añado un listener a la tabla de pedidos para manejar la selección de un pedido.
        tPedidos.getSelectionModel().selectedItemProperty().addListener((observableValue, pedido, t1) -> {
            Sesion.setPedido(t1);
        });
    }

    private void cargarLista() {
        observablePedidos.setAll(Sesion.getUsuario().getPedidos());
        for (Pedido pedidos : observablePedidos) {
            Double totalPedidos = calcularTotalPedidos(pedidos);
            pedidos.setTotal(totalPedidos);
        }
        tPedidos.setItems(observablePedidos);
    }

    private Double calcularTotalPedidos(Pedido pedido) {
        Double total  = 0.0;

        for (Item items : pedido.getItems()){
            total += items.getProducto().getPrecio() * items.getCantidad();
        }
        return total;
    }


    public void salir(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login.fxml");
    }


    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Creado por");
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");
        alert.showAndWait();
    }
}