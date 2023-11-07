package com.example.gestordepedidos.controller;

import com.example.gestordepedidos.HelloApplication;
import com.example.gestordepedidos.domain.conexionbbdd.DBConnection;
import com.example.gestordepedidos.domain.pedido.Pedido;
import com.example.gestordepedidos.domain.pedido.PedidoDAOImp;
import com.example.gestordepedidos.domain.sesion.Sesion;
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


    /**
     * Inicializo la vista principal del usuario y se establecen los controladores de eventos en los elementos de la
     * interfaz de usuario.
     * Este método se ejecuta al cargar la vista y configura la visualización de los pedidos del usuario actual.
     *
     * @param url            Se refiere a la bicación relativa de la raíz del documento a la que se resuelve la URL
     *                      que se le ha pasado como argumento.
     * @param resourceBundle Hace referencia a los recursos de un objeto ResourceBundle para esta inicialización.
     */
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
        PedidoDAOImp daoPedido = new PedidoDAOImp(DBConnection.getConnection());
        Sesion.setPedidos(daoPedido.loadAll(Sesion.getUsuario().getId_usuario()));
        observablePedidos.addAll(Sesion.getPedidos());
        tPedidos.setItems(observablePedidos);

        // Añado un listener a la tabla de pedidos para manejar la selección de un pedido.
        tPedidos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            seleccionarPedido(tPedidos.getSelectionModel().getSelectedItem());
        });
    }

    /**
     * Maneja la selección de un pedido en la tabla de pedidos.
     * Al seleccionar un pedido, se actualiza el pedido actual en la sesión y se carga la vista de detalles del pedido.
     *
     * @param pedido Hace referencia al pedido seleccionado en la tabla.
     */
    private void seleccionarPedido(Pedido pedido) {
        Sesion.setPedido(pedido);
        Sesion.setPosicion(tPedidos.getSelectionModel().getSelectedIndex());
        HelloApplication.loadFXMLDetails("details-view.fxml");
    }

    /**
     * Maneja el evento de salida del usuario. Cierra la sesión actual y carga la vista de inicio de sesión.
     *
     * @param actionEvent Se refiere al evento de acción que desencadena la llamada a este método.
     */
    public void salir(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login.fxml");
    }

    /**
     * Muestra una ventana emergente de información que describe la aplicación y su creador.
     *
     * @param actionEvent Hace referencia al evento de acción que desencadena la llamada a este método.
     */
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Creado por");
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");
        alert.showAndWait();
    }
}