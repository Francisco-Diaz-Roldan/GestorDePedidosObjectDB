package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;

import com.example.gestordepedidoshibernate.domain.pedido.PedidoDAO;
import com.example.gestordepedidoshibernate.domain.sesion.Sesion;
import com.example.gestordepedidoshibernate.domain.usuario.UsuarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private final PedidoDAO pedidoDAO = new PedidoDAO(); //Creo una instancia de PedidoDAO.
    @javafx.fxml.FXML
    private Button btnAdd;
    @javafx.fxml.FXML
    private Button btnDelete;

    /**
     * Inicializa la vista principal del usuario.
     * Configura las columnas de la tabla de pedidos, muestra el nombre del usuario y carga la lista de pedidos.
     *
     * @param url            La ubicación del archivo FXML.
     * @param resourceBundle Recursos específicos del idioma.
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
            String usuario = String.valueOf(fila.getValue().getUsuario().getNombre());
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

        //Si le doy 2 veces al pedido realiza la acción del
        tPedidos.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Pedido selectedPedido = tPedidos.getSelectionModel().getSelectedItem();
                if (selectedPedido != null) {
                    Sesion.setPedido(selectedPedido);
                    HelloApplication.loadFXMLDetails("details-view.fxml");
                }
            }
        });
    }

    /**
     * Carga la lista de pedidos para el usuario actual y actualiza la tabla.
     */

    private void cargarLista() {
        observablePedidos.setAll(Sesion.getUsuario().getPedidos());
        for (Pedido pedidos : observablePedidos) {
            Double totalPedidos = calcularTotalPedidos(pedidos);
            pedidos.setTotal(totalPedidos);
        }
        tPedidos.setItems(observablePedidos);
    }

    /**
     * Calcula el total de los pedidos sumando los precios de los productos.
     *
     * @param pedido El pedido para el cual se calcula el total.
     * @return El total calculado.
     */

    private Double calcularTotalPedidos(Pedido pedido) {
        Double total  = 0.0;

        for (Item items : pedido.getItems()){
            total += items.getProducto().getPrecio() * items.getCantidad();
        }
        return total;
    }

    /**
     * Maneja el evento de salir, cerrando la sesión del usuario.
     *
     * @param actionEvent Evento de acción que desencadena el cierre de sesión.
     */

    public void salir(ActionEvent actionEvent) {
        Sesion.setUsuario(null);
        HelloApplication.loadFXMLLogin("login.fxml");
    }

    /**
     * Muestra información sobre el creador de la aplicación.
     *
     * @param actionEvent Evento de acción que desencadena la visualización de información.
     */

    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Creado por");
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");
        alert.showAndWait();
    }

    /**
     * Añade un nuevo pedido y carga la vista de detalles del pedido.
     *
     * @param actionEvent Evento de acción que desencadena la creación de un nuevo pedido.
     */

    @javafx.fxml.FXML
    public void addPedido(ActionEvent actionEvent) {
        Pedido pedidoAnadido = new Pedido();

        try (Session s = HibernateUtils.getSessionFactory().openSession()){
            //Obtengo el último código de los pedidos
            Query<String> q = s.createQuery("select max(p.codigo_pedido) from Pedido p", String.class);
            String ultimoCodigoPedido = q.uniqueResult();

            //Aumento en 1 el último código de los pedidos
            int ultimoNumero = Integer.parseInt(ultimoCodigoPedido.substring(4));
            int nuevoNumero =ultimoNumero+1;
            String nuevoCodigoPedido = "PED-" + String.format("%03d", nuevoNumero);

            //Establezcp el nuevo código en los pedidos
            pedidoAnadido.setCodigo_pedido(nuevoCodigoPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Establecer la fecha actual por defecto
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormat.format(new Date());
        pedidoAnadido.setFecha(fechaActual);

        pedidoAnadido.setUsuario(Sesion.getUsuario());
        pedidoAnadido.setId_pedido(0);

        if (pedidoAnadido.getItems().isEmpty()) {
            pedidoAnadido.setTotal(0.0);
        }

        // Agregar el nuevo pedido a la lista observable
        observablePedidos.add(pedidoAnadido);

        // Actualizar la tabla
        tPedidos.setItems(observablePedidos);
        Sesion.setPedido((new PedidoDAO()).save(pedidoAnadido));
        Sesion.setPedido(pedidoAnadido);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Enhorabuena");
        alert.setHeaderText("El pedido se ha añadido correctamente");
        alert.setContentText("El código del pedido es: " + Sesion.getPedido().getCodigo_pedido());
        alert.showAndWait();

        HelloApplication.loadFXMLDetails("details-view-controller.fxml");


    }

    /**
     * Elimina el pedido seleccionado.
     *
     * @param actionEvent Evento de acción que desencadena la eliminación del pedido.
     */

    @javafx.fxml.FXML
    public void deletePedido(ActionEvent actionEvent) {
        Pedido pedidoSeleccionado = tPedidos.getSelectionModel().getSelectedItem();

        if (pedidoSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿De verdad que quieres borrar el pedido: " + pedidoSeleccionado.getCodigo_pedido()
                    + "?");
            var result = alert.showAndWait().get();

            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                pedidoDAO.delete(pedidoSeleccionado);
                observablePedidos.remove(pedidoSeleccionado);
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona el pedido a eliminar.");
            alert.showAndWait();
        }
    }
}