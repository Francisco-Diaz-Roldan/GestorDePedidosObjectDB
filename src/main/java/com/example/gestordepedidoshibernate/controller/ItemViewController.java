package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;
import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.item.ItemDAO;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import com.example.gestordepedidoshibernate.domain.producto.Producto;
import com.example.gestordepedidoshibernate.domain.producto.ProductoDAO;
import com.example.gestordepedidoshibernate.domain.sesion.Sesion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de ítems.
 */
public class ItemViewController implements Initializable {
    @javafx.fxml.FXML
    private Label labelInfoCantidad;
    @javafx.fxml.FXML
    private Spinner<Integer> spCantidad;
    @javafx.fxml.FXML
    private ComboBox<Producto> comboProducto;
    private ObservableList<Producto> observableListProductos;

    /**
     * Inicializa la vista de gestión de ítems.
     *
     * @param url            La ubicación del archivo FXML.
     * @param resourceBundle Recursos específicos del idioma.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableListProductos = FXCollections.observableArrayList();
        ProductoDAO productoDAO = new ProductoDAO();
        observableListProductos.setAll(productoDAO.getAll());
        comboProducto.setItems(observableListProductos);
        comboProducto.getSelectionModel().selectFirst();
        spCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
        labelInfoCantidad.setText("Cantidad disponible: " + comboProducto.getSelectionModel().getSelectedItem().getCantidad_disponible());
        comboProducto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                labelInfoCantidad.setText("Cantidad disponible: " + newValue.getCantidad_disponible());
            }
        });
    }

    /**
     * Método para agregar un ítem al pedido.
     *
     * @param actionEvent Evento de acción que desencadena la adición de un nuevo ítem.
     */
    public void add(ActionEvent actionEvent) {

        // Se crea una instancia de Pedido con el pedido actual de la sesión.
        Pedido pedido = Sesion.getPedido();

        // Si el pedido es distinto de nulo se crea un nuevo item para ese pedido y se retorna a la ventana de DetallesPedidoController.
        Producto productoSeleccionado = comboProducto.getSelectionModel().getSelectedItem();
        Integer cantidadAgregada = spCantidad.getValue();
        Integer cantidadDisponible = productoSeleccionado.getCantidad_disponible();

        if (cantidadAgregada > cantidadDisponible) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cantidad no disponible");
            alert.setHeaderText("No es posible seleccionar esa cantidad de producto");
            alert.setContentText("Cantidad de producto disponible: " + productoSeleccionado.getCantidad_disponible());
            alert.showAndWait();
        } else {
            Item item = new Item();
            item.setCodigo_pedido(pedido);
            item.setCantidad(spCantidad.getValue());
            item.setProducto(productoSeleccionado);

            Sesion.setItem((new ItemDAO()).save(item));
            Sesion.setItem(item);

            HelloApplication.loadFXMLDetails("details-view.fxml");
        }
    }

    /**
     * Método para salir de la sesión.
     *
     * @param actionEvent Evento de acción que desencadena la salida.
     */
    public void salir(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login.fxml");
    }

    /**
     * Método para volver atrás en la aplicación.
     *
     * @param actionEvent Evento de acción que desencadena el retorno.
     * @throws IOException Excepción de entrada/salida.
     */
    public void volverAtras(ActionEvent actionEvent) throws IOException {
        HelloApplication.loadFXMLUser("details-view.fxml");
    }

    /**
     * Muestra información acerca del creador de la aplicación.
     *
     * @param actionEvent Evento de acción que desencadena la visualización de la información.
     */
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de ");
        alert.setHeaderText("Creado por ");
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");
        alert.showAndWait();
    }
}