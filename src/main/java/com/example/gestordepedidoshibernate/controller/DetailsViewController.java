package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;
import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.item.ItemDAO;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import com.example.gestordepedidoshibernate.domain.pedido.PedidoDAO;
import com.example.gestordepedidoshibernate.domain.sesion.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsViewController implements Initializable {
    @javafx.fxml.FXML
    private TableColumn<Item, String> cIdItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cCodigoPedido;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cProducto;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cCantidad;
    @javafx.fxml.FXML
    private TableView<Item> tItem;

    private ObservableList<Item> observableListItem;
    private ItemDAO itemDAO = new ItemDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuración de las columnas de la tabla y carga de datos.
        cIdItem.setCellValueFactory((fila) -> {
            String id_item = String.valueOf(fila.getValue().getId_item());
            return new SimpleStringProperty(id_item);
        });

        cCodigoPedido.setCellValueFactory((fila) -> {
            String codigo_pedido = String.valueOf(fila.getValue().getCodigo_pedido().getCodigo_pedido());
            return new SimpleStringProperty(codigo_pedido);
        });

        cCantidad.setCellValueFactory((fila) -> {
            String cantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(cantidad);
        });

        cProducto.setCellValueFactory((fila) -> {
            String producto = String.valueOf(fila.getValue().getProducto().getNombre() + "/"
                    + fila.getValue().getProducto().getPrecio() + "€");
            return new SimpleStringProperty(producto);
        });

        // Creo una lista observable para contener los elementos (detalles de pedidos) que se mostrarán en la tabla.
        observableListItem = FXCollections.observableArrayList();

       Sesion.setPedido((new PedidoDAO()).get(Sesion.getPedido().getId_pedido()));
        System.out.println(Sesion.getPedido());
        System.out.println(Sesion.getItems());
        // Agrego los detalles de los pedidos (items) cargados previamente a la lista observable.
        observableListItem.setAll(Sesion.getPedido().getItems());
        // Establezco la lista observable como el conjunto de elementos que se mostrarán en la tabla.
        tItem.setItems(observableListItem);
    }

    public void salir(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login.fxml");
    }


    public void volverAtras(ActionEvent actionEvent) throws IOException {
        HelloApplication.loadFXMLUser("user-view.fxml");
    }


    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de ");
        alert.setHeaderText("Creado por ");
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void addItem(ActionEvent actionEvent) {
        var item = new Item();
        Sesion.setItem(item);
        HelloApplication.loadFXMLItem("item-controller.fxml");

    }

    @javafx.fxml.FXML
    public void deleteItem(ActionEvent actionEvent) {
        Item itemSeleccionado = tItem.getSelectionModel().getSelectedItem();

        if (itemSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿De verdad que quieres borrar el item: " + itemSeleccionado.getId_item() +
                    " del producto " + itemSeleccionado.getProducto().getNombre() + "?");
            var result = alert.showAndWait().get();

            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                itemDAO.delete(itemSeleccionado);
                observableListItem.remove(itemSeleccionado);
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona el pedido a eliminar.");
            alert.showAndWait();
        }
    }

}