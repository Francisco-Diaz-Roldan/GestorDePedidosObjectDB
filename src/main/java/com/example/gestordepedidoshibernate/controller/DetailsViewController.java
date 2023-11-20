package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;
import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.sesion.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controlador de la vista de detallada de los pedidos.
 *
 * Esta clase se encarga de gestionar la vista que muestra los detalles de un pedido.
 * Permite ver los pedidos y su información asociada.
 */
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
    private TableView tItem;

    private ObservableList<Item> observableListItem;

    /**
     * Inicializa la vista de detalles de pedidos.
     *
     * Este método se ejecuta al cargar la vista y configura las columnas de la tabla y carga los datos de los pedidos.
     *
     * @param url            Se refiere a la ubicación relativa al archivo FXML.
     * @param resourceBundle Se refiere a los recursos locales específicos de la ubicación.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuración de las columnas de la tabla y carga de datos.
        cIdItem.setCellValueFactory((fila) -> {
            String id_item = String.valueOf(fila.getValue().getId_item());
            return new SimpleStringProperty(id_item);
        });

        cCodigoPedido.setCellValueFactory((fila) -> {
            String codigo_pedido = fila.getValue().getCodigo_pedido();
            return new SimpleStringProperty(codigo_pedido);
        });

        cCantidad.setCellValueFactory((fila) -> {
            String cantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(cantidad);
        });

        cProducto.setCellValueFactory((fila) -> {
            String producto = String.valueOf(fila.getValue().getProducto());
            return new SimpleStringProperty(producto);
        });

        // Creo una lista observable para contener los elementos (detalles de pedidos) que se mostrarán en la tabla.
        observableListItem = FXCollections.observableArrayList();
        // Creo una instancia del DAO para acceder a los detalles de los pedidos (items) desde la base de datos.
        ItemDAOImp daoItem = new ItemDAOImp(DBConnection.getConnection());
        // Cargo los detalles de los pedidos (items) relacionados con el pedido actual.
        System.out.println(Sesion.getPedido());
        Sesion.setItems(daoItem.loadAll(Sesion.getPedido().getCodigo_pedido()));
        System.out.println(Sesion.getItems());
        // Agrego los detalles de los pedidos (items) cargados previamente a la lista observable.
        observableListItem.addAll(Sesion.getItems());
        // Establezco la lista observable como el conjunto de elementos que se mostrarán en la tabla.
        tItem.setItems(observableListItem);
    }

    /**
     * Maneja el evento de salida de la sesión.
     *
     * Cierra la sesión actual y redirige al usuario a la pantalla de inicio de sesión.
     *
     * @param actionEvent Se refiere al evento de acción que desencadena esta función.
     */
    public void salir(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login.fxml");
    }

    /**
     * Maneja el evento de volver atrás.
     *
     * Permite al usuario regresar a la vista anterior donde puede ver sus pedidos.
     *
     * @param actionEvent Hace referencia al evento de acción que desencadena esta función.
     * @throws IOException En caso de error al cargar la vista de pedidos.
     */
    public void volverAtras(ActionEvent actionEvent) throws IOException {
        HelloApplication.loadFXMLUser("user-view.fxml");
    }

    /**
     * Muestra información "Acerca de".
     *
     * Muestra una ventana emergente con información sobre la aplicación y su creador.
     *
     * @param actionEvent El evento de acción que desencadena esta función.
     */
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de ");
        alert.setHeaderText("Creado por ");
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");
        alert.showAndWait();
    }
}