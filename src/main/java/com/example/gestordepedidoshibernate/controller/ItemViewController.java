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
    private Label labelInfoCantidad;  // Etiqueta para mostrar información relacionada con la cantidad.

    @javafx.fxml.FXML
    private Spinner<Integer> spCantidad;  // Spinner para seleccionar la cantidad de un producto.

    @javafx.fxml.FXML
    private ComboBox<Producto> comboProducto;  // ComboBox para seleccionar un producto de una lista.

    private ObservableList<Producto> observableListProductos;  // Lista observable para contener objetos de la clase
    // Producto.


    /**
     * Inicializa la vista de gestión de ítems.
     *
     * @param url            La ubicación del archivo FXML.
     * @param resourceBundle Recursos específicos del idioma.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Creación de una lista observable para contener objetos de la clase Producto.
        observableListProductos = FXCollections.observableArrayList();

        // Creación de una instancia de la clase ProductoDAO para acceder a la base de datos de productos.
        ProductoDAO productoDAO = new ProductoDAO();

        // Carga de todos los productos desde la base de datos y los agrega a la lista observable.
        observableListProductos.setAll(productoDAO.getAll());

        // Establece la lista observable como el conjunto de elementos disponibles en el ComboBox.
        comboProducto.setItems(observableListProductos);

        // Selecciona el primer elemento en el ComboBox.
        comboProducto.getSelectionModel().selectFirst();

        // Configuración del Spinner para elegir la cantidad de productos.
        spCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));

        // Configuración inicial del texto de la etiqueta que muestra la cantidad disponible del producto seleccionado.
        labelInfoCantidad.setText("Cantidad disponible: " + comboProducto.getSelectionModel().getSelectedItem()
                .getCantidad_disponible());

        // Agrega un listener para actualizar la etiqueta cuando se selecciona un nuevo producto en el ComboBox.
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
        // Se obtiene el pedido actual de la sesión.
        Pedido pedido = Sesion.getPedido();

        // Se obtiene el producto seleccionado del ComboBox y la cantidad seleccionada del Spinner.
        Producto productoSeleccionado = comboProducto.getSelectionModel().getSelectedItem();
        Integer cantidadAgregada = spCantidad.getValue();
        Integer cantidadDisponible = productoSeleccionado.getCantidad_disponible();

        // Se verifica si la cantidad seleccionada es mayor que la cantidad disponible del producto.
        if (cantidadAgregada > cantidadDisponible) {
            // Si es mayor, se muestra una alerta de error.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cantidad no disponible");
            alert.setHeaderText("No es posible seleccionar esa cantidad de producto");
            alert.setContentText("Unidades disponibles : " + productoSeleccionado.getCantidad_disponible());
            alert.showAndWait();
        } else {
            // Si la cantidad seleccionada es válida, se crea un nuevo item y se guarda en la base de datos.
            Item item = new Item();
            item.setCodigo_pedido(pedido);
            item.setCantidad(spCantidad.getValue());
            item.setProducto(productoSeleccionado);

            // Se guarda el item en la base de datos y se establece como el item actual en la sesión.
            Sesion.setItem((new ItemDAO()).save(item));
            Sesion.setItem(item);

            // Se carga la vista de detalles del pedido.
            HelloApplication.loadFXMLDetails("details-view.fxml");
        }
    }


    /**
     * Método para salir de la sesión.
     *
     * @param actionEvent Evento de acción que desencadena la salida.
     */
    public void salir(ActionEvent actionEvent) {
        // Establece el usuario en la sesión como null, indicando que no hay usuario activo.
        Sesion.setUsuario(null);

        // Carga el FXML de la pantalla de inicio de sesión utilizando el método loadFXMLLogin de la clase
        // HelloApplication.
        HelloApplication.loadFXMLLogin("login.fxml");
    }


    /**
     * Método para volver atrás en la aplicación.
     *
     * @param actionEvent Evento de acción que desencadena el retorno.
     * @throws IOException Excepción de entrada/salida.
     */
    public void volverAtras(ActionEvent actionEvent) throws IOException {
        // Carga el FXML de la vista de detalles del usuario utilizando el método loadFXMLUser de la clase
        // HelloApplication.
        HelloApplication.loadFXMLUser("details-view.fxml");
    }


    /**
     * Muestra información acerca del creador de la aplicación.
     *
     * @param actionEvent Evento de acción que desencadena la visualización de la información.
     */
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        // Crea un cuadro de diálogo de información.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Configura el título del cuadro de diálogo.
        alert.setTitle("Acerca de ");

        // Configura el encabezado del cuadro de diálogo.
        alert.setHeaderText("Creado por ");

        // Configura el contenido del cuadro de diálogo con información sobre el creador.
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");

        // Muestra el cuadro de diálogo de información y espera hasta que el usuario lo cierre.
        alert.showAndWait();
    }

}