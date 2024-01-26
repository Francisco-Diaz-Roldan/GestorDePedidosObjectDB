package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;
import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;

import com.example.gestordepedidoshibernate.domain.pedido.PedidoDAO;
import com.example.gestordepedidoshibernate.domain.sesion.Sesion;
import com.example.gestordepedidoshibernate.domain.usuario.UsuarioDAO;
import com.example.gestordepedidoshibernate.objectdbutils.ObjectDBUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
            // Obtiene el valor del identificador del pedido y lo convierte a una cadena.
            String id_pedido = String.valueOf(fila.getValue().getId_pedido());

            // Devuelve una propiedad simple de cadena que representa el identificador del pedido.
            return new SimpleStringProperty(id_pedido);
        });

        cCodigoPedido.setCellValueFactory((fila) -> {
            // Obtiene el valor del código del pedido y lo convierte a una cadena.
            String codigo_pedido = String.valueOf(fila.getValue().getCodigo_pedido());

            // Devuelve una propiedad simple de cadena que representa el código del pedido.
            return new SimpleStringProperty(codigo_pedido);
        });

        cFecha.setCellValueFactory((fila) -> {
            // Obtiene el valor de la fecha del pedido y lo convierte a una cadena.
            String fecha = String.valueOf(fila.getValue().getFecha());

            // Devuelve una propiedad simple de cadena que representa la fecha del pedido.
            return new SimpleStringProperty(fecha);
        });

        cUsuario.setCellValueFactory((fila) -> {
            // Obtiene el valor del nombre del usuario asociado al pedido y lo convierte a una cadena.
            String usuario = String.valueOf(fila.getValue().getUsuario().getNombre());

            // Devuelve una propiedad simple de cadena que representa el nombre del usuario asociado al pedido.
            return new SimpleStringProperty(usuario);
        });

        cTotal.setCellValueFactory((fila) -> {
            // Obtiene el valor del total del pedido y lo convierte a una cadena.
            String total = String.valueOf(fila.getValue().getTotal());

            // Devuelve una propiedad simple de cadena que representa el total del pedido.
            return new SimpleStringProperty(total);
        });

        // Muestra el nombre del usuario en una etiqueta de bienvenida.
        lblUsuario.setText("Bienvenido " + Sesion.getUsuario().getNombre());

        // Crear una lista observable para los pedidos y carga los pedidos del usuario actual.
        observablePedidos = FXCollections.observableArrayList();

        // Recarga los pedidos del usuario actual desde la base de datos.
        Sesion.setUsuario((new UsuarioDAO()).get(Sesion.getUsuario().getId_usuario()));
        cargarLista();

        // Agrega los pedidos a la lista observable.
        observablePedidos.addAll(Sesion.getPedidos());

        // Añade un listener a la tabla de pedidos para manejar la selección de un pedido.
        tPedidos.getSelectionModel().selectedItemProperty().addListener((observableValue, pedido, t1) -> {
            Sesion.setPedido(t1);
        });

        // Añade un evento a la tabla de pedidos para manejar doble clic y carga los detalles del pedido.
        tPedidos.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Pedido selectedPedido = tPedidos.getSelectionModel().getSelectedItem();
                if (selectedPedido != null) {
                    // Establece el pedido seleccionado en la sesión y carga la vista de detalles.
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
        // Obtiene la lista de pedidos del usuario actual y la establece en la lista observable.
        observablePedidos.setAll(Sesion.getUsuario().getPedidos());

        // Itera sobre cada pedido en la lista observable.
        for (Pedido pedidos : observablePedidos) {
            // Calcula el total de cada pedido y lo establece en el pedido correspondiente.
            Double totalPedidos = calcularTotalPedidos(pedidos);
            pedidos.setTotal(totalPedidos);
        }

        // Establece la lista observable como el conjunto de elementos que se mostrarán en la tabla de pedidos.
        tPedidos.setItems(observablePedidos);
    }

    /**
     * Calcula el total de los pedidos sumando los precios de los productos.
     *
     * @param pedido El pedido para el cual se calcula el total.
     * @return El total calculado.
     */

    private Double calcularTotalPedidos(Pedido pedido) {
        // Inicializa el total en 0.0.
        Double total = 0.0;

        // Itera sobre cada elemento (Item) en el pedido.
        for (Item items : pedido.getItems()) {
            // Calcula el subtotal para cada elemento y lo suma al total.
            total += items.getProducto().getPrecio() * items.getCantidad();
        }
        // Devuelve el total del pedido.
        return total;
    }


    /**
     * Maneja el evento de salir, cerrando la sesión del usuario.
     *
     * @param actionEvent Evento de acción que desencadena el cierre de sesión.
     */

    public void salir(ActionEvent actionEvent) {
        // Establece el usuario en la sesión como null, indicando que no hay usuario activo.
        Sesion.setUsuario(null);

        // Carga el FXML de la pantalla de inicio de sesión utilizando el método loadFXMLLogin de la clase
        // HelloApplication.
        HelloApplication.loadFXMLLogin("login.fxml");
    }

    /**
     * Muestra información sobre el creador de la aplicación.
     *
     * @param actionEvent Evento de acción que desencadena la visualización de información.
     */

    public void mostrarAcercaDe(ActionEvent actionEvent) {
        // Crea un cuadro de diálogo de información (clase Alert).
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Configura el título del cuadro de diálogo.
        alert.setTitle("Acerca de");

        // Configura el encabezado del cuadro de diálogo.
        alert.setHeaderText("Creado por");

        // Configura el contenido del cuadro de diálogo con información sobre el creador de la aplicación.
        alert.setContentText("Francisco Díaz Roldán desde 2ºDAM");

        // Muestra el cuadro de diálogo de información y espera hasta que el usuario lo cierre.
        alert.showAndWait();
    }


    /**
     * Añade un nuevo pedido y carga la vista de detalles del pedido.
     *
     * @param actionEvent Evento de acción que desencadena la creación de un nuevo pedido.
     */

    @javafx.fxml.FXML
    public void addPedido(ActionEvent actionEvent) {
        Pedido nuevoPedido = new Pedido();
        setId(nuevoPedido);
        setCodigoPedido(nuevoPedido);
        setFecha(nuevoPedido);
        nuevoPedido.setUsuario(Sesion.getUsuario());

        //Si el pedido no tiene items dentro el total se establece a '0.0'.
        if (nuevoPedido.getItems().isEmpty()) {
            nuevoPedido.setTotal(0.0);
        }

        //Agrega el nuevo pedido a la lista Observable.
        observablePedidos.add(nuevoPedido);

        //Actualiza la tabla.
        tPedidos.setItems(observablePedidos);
        Sesion.setPedido((new PedidoDAO()).save(nuevoPedido));
        Sesion.setPedido(nuevoPedido);

        //Alerta que indica que el pedido fue creado con éxito.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Éxito!");
        alert.setHeaderText("Tu pedido ha sido creado");
        alert.setContentText("Código de pedido: " + Sesion.getPedido().getCodigo_pedido());
        alert.showAndWait();

        //Después de la alerta, lleva a la ventana DetallesPedidoController del respectivo pedido.
        HelloApplication.loadFXMLDetails("details-view.fxml");
    }

    private void setId(Pedido pedido) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Integer> query = entityManager
                    .createQuery("select MAX(p.id_pedido) FROM Pedido p", Integer.class);
            Integer ultimoId = query.getSingleResult();
            if (ultimoId != null) {
                //Incrementa y establece el último id.
                pedido.setId_pedido(ultimoId + 1);
            } else { //Si no hay pedidos en la Base de Datos, el id será por defecto '1'.
                pedido.setId_pedido(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Establece el código de pedido para el nuevo pedido.
     *
     * @param pedido Pedido al que se le establecerá el nuevo código de pedido.
     */
    private void setCodigoPedido(Pedido pedido) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<String> query = entityManager
                    .createQuery("select MAX(p.codigo_pedido) FROM Pedido p", String.class);
            String ultimoCodigoPedido = query.getSingleResult();
            if (ultimoCodigoPedido != null) {
                //Incrementa el último código de pedido.
                Integer ultimoNumero = Integer.parseInt(ultimoCodigoPedido.substring(4));
                Integer nuevoNumero = ultimoNumero + 1;
                String nuevoCodigoPedido = "PED-" + String.format("%03d", nuevoNumero);
                //Establece el nuevo código de pedido.
                pedido.setCodigo_pedido(nuevoCodigoPedido);
            } else { //Si no hay pedidos en la Base de Datos, el código de pedido será por defecto 'PED-001'.
                pedido.setCodigo_pedido("PED-001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Establece la fecha del nuevo pedido.
     *
     * @param pedido Pedido al que se le establecerá la fecha.
     */
    private void setFecha(Pedido pedido) {
        //Establece la fecha actual por defecto.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormat.format(new Date());
        pedido.setFecha(fechaActual);
    }

    /**
     * Elimina el pedido seleccionado.
     *
     * @param actionEvent Evento de acción que desencadena la eliminación del pedido.
     */

    @javafx.fxml.FXML
    public void deletePedido(ActionEvent actionEvent) {
        // Obtiene el pedido seleccionado de la tabla de pedidos.
        Pedido pedidoSeleccionado = tPedidos.getSelectionModel().getSelectedItem();

        if (pedidoSeleccionado != null) {
            // Crea un cuadro de diálogo de confirmación.
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿De verdad que quieres borrar el pedido: "
                    + pedidoSeleccionado.getCodigo_pedido() + "?");

            // Muestra el cuadro de diálogo y espera la respuesta del usuario.
            var result = alert.showAndWait().get();

            // Si el usuario confirma la eliminación.
            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                // Elimina el pedido de la base de datos.
                pedidoDAO.delete(pedidoSeleccionado);

                // Elimina el pedido de la lista observable de pedidos.
                observablePedidos.remove(pedidoSeleccionado);
            }
        } else {
            // Si no se selecciona ningún pedido, muestra un cuadro de diálogo de advertencia.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, selecciona el pedido a eliminar.");
            alert.showAndWait();
        }
    }
}