package com.example.gestordepedidos.controller;

import com.example.gestordepedidos.HelloApplication;
import com.example.gestordepedidos.domain.conexionbbdd.DBConnection;
import com.example.gestordepedidos.domain.pedido.Pedido;
import com.example.gestordepedidos.domain.pedido.PedidoDAOImp;
import com.example.gestordepedidos.domain.sesion.Sesion;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {
    @javafx.fxml.FXML
    private Label lblUsuario;
    @javafx.fxml.FXML
    private TableView<Pedido> tPedidos;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cIdPedido;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cCodigoPedido;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cUsuario;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cTotal;

    private ObservableList<Pedido> observablePedidos;


        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    cIdPedido.setCellValueFactory((fila) ->{
        String id_pedido = String.valueOf(fila.getValue().getId_pedido());
        return new SimpleStringProperty(id_pedido);
    });

    cCodigoPedido.setCellValueFactory((fila)->{
        String codigo_pedido = String.valueOf(fila.getValue().getCodigo_pedido());
        return new SimpleStringProperty(codigo_pedido);
    });

    cFecha.setCellValueFactory((fila)->{
        String fecha = String.valueOf(fila.getValue().getFecha());
        return new SimpleStringProperty(fecha);
    });

    cUsuario.setCellValueFactory((fila)->{
        String usuario = String.valueOf(fila.getValue().getUsuario());
        return new SimpleStringProperty(usuario);
    });

    cTotal.setCellValueFactory((fila)->{
        String total = String.valueOf(fila.getValue().getTotal());
        return new SimpleStringProperty(total);
    });

    lblUsuario.setText("Bienvenido "+ Sesion.getUsuario().getNombre());

    observablePedidos = FXCollections.observableArrayList();
    PedidoDAOImp daoPedido = new PedidoDAOImp(DBConnection.getConnection());
    Sesion.setPedidos(daoPedido.loadAll(Sesion.getUsuario().getId_usuario()));
    observablePedidos.addAll(Sesion.getPedido());
    tPedidos.setItems(observablePedidos);

    tPedidos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        seleccionarPedido(tPedidos.getSelectionModel().getSelectedItem());
    });

}

    private void seleccionarPedido(Pedido pedido) {
            Sesion.setPedido(pedido);
            Sesion.setPosicion(tPedidos.getSelectionModel().getSelectedIndex());
        HelloApplication.loadFXMLDetails("details-view.fxml");
    }

    public void salir(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login.fxml");
    }

    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de ");
        alert.setHeaderText("Creado por ");
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");
        alert.showAndWait();
    }
}
