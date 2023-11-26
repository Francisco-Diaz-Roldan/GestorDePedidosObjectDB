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
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controlador para la vista de detalles de pedidos.
 */
public class DetailsViewController implements Initializable {
    // Definición de las columnas y la tabla en la interfaz gráfica de usuario.

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

    // Lista observable para contener los elementos (detalles de pedidos) que se mostrarán en la tabla.
    private ObservableList<Item> observableListItem;
    // Instancia de ItemDAO para acceder a operaciones de base de datos relacionadas con Item.
    private ItemDAO itemDAO = new ItemDAO();
    @javafx.fxml.FXML
    private Button btnAdd;
    @javafx.fxml.FXML
    private Button btnDelete;

    /**
     * Inicializa la vista de detalles de pedidos.
     *
     * @param url            La ubicación del archivo FXML.
     * @param resourceBundle Recursos específicos del idioma.
     */
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

        // Carga de detalles de los pedidos previamente cargados en la sesión.
        Sesion.setPedido((new PedidoDAO()).get(Sesion.getPedido().getId_pedido()));
        System.out.println(Sesion.getPedido());
        System.out.println(Sesion.getItems());
        // Agrego los detalles de los pedidos (items) cargados previamente a la lista observable.
        observableListItem.setAll(Sesion.getPedido().getItems());
        // Establezco la lista observable como el conjunto de elementos que se mostrarán en la tabla.
        tItem.setItems(observableListItem);

        // Actualiza la información del pedido.
        actualizarPedido();
    }

    /**
     * Método para salir de la sesión.
     *
     * @param actionEvent Evento de acción que desencadena la salida.
     */
    @Deprecated
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
    @Deprecated
    public void volverAtras(ActionEvent actionEvent) throws IOException {
        HelloApplication.loadFXMLUser("user-view.fxml");
    }

    /**
     * Muestra información acerca del creador de la aplicación.
     *
     * @param actionEvent Evento de acción que desencadena la visualización de la información.
     */
    @Deprecated
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de ");
        alert.setHeaderText("Creado por ");
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");
        alert.showAndWait();
    }

    /**
     * Añade un nuevo ítem al pedido.
     *
     * @param actionEvent Evento de acción que desencadena la adición de un nuevo ítem.
     */
    @javafx.fxml.FXML
    public void addItem(ActionEvent actionEvent) {
        var item = new Item();
        Sesion.setItem(item);
        HelloApplication.loadFXMLItem("item-view.fxml");
    }

    /**
     * Elimina un ítem del pedido.
     *
     * @param actionEvent Evento de acción que desencadena la eliminación de un ítem.
     */
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

                // Calcula el nuevo total del pedido y lo actualiza en la Base de Datos.
                Pedido pedidoActual = Sesion.getPedido();
                Double nuevoTotal = calcularTotal(pedidoActual) - (itemSeleccionado.getProducto().getPrecio() *
                        itemSeleccionado.getCantidad());
                System.out.println(nuevoTotal);
                pedidoActual.setTotal(nuevoTotal);

                // Actualiza el total del pedido en la Base de Datos
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.update(pedidoActual);
            }
        } else {
            // Muestra un mensaje de error o advertencia al usuario si no se ha seleccionado ningún pedido para eliminar.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Selecciona el item a eliminar.");
            alert.showAndWait();
        }
    }

    /**
     * Calcula el total de un pedido.
     *
     * @param pedidoActual Pedido del cual calcular el total.
     * @return Total calculado del pedido.
     */
    private Double calcularTotal(Pedido pedidoActual) {
        Double total = 0.0;

        for (Item items : pedidoActual.getItems()) {
            total += items.getProducto().getPrecio() * items.getCantidad();
        }
        return total;
    }

    /**
     * Actualiza la información del pedido en la base de datos.
     */
    private void actualizarPedido() {
        Pedido pedidoActual = Sesion.getPedido();
        Double totalActual = calcularTotal(pedidoActual);
        pedidoActual.setTotal(totalActual);

        try {
            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.update(pedidoActual);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}