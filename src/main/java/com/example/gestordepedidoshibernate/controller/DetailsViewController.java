package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;
import com.example.gestordepedidoshibernate.domain.item.Item;
import com.example.gestordepedidoshibernate.domain.item.ItemDAO;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import com.example.gestordepedidoshibernate.domain.pedido.PedidoDAO;
import com.example.gestordepedidoshibernate.domain.sesion.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
/**
 * Controlador para la vista de detalles de pedidos.
 */
public class DetailsViewController implements Initializable {
    // Defino las columnas y la tabla en la interfaz gráfica de usuario.
    @javafx.fxml.FXML
    private TableColumn<Item, String> cIdItem;  // Columna para mostrar el identificador del ítem.

    @javafx.fxml.FXML
    private TableColumn<Item, String> cCodigoPedido;  // Columna para mostrar el código del pedido del ítem.

    @javafx.fxml.FXML
    private TableColumn<Item, String> cProducto;  // Columna para mostrar información del producto del ítem.

    @javafx.fxml.FXML
    private TableColumn<Item, String> cCantidad;  // Columna para mostrar la cantidad del ítem.

    @javafx.fxml.FXML
    private TableView<Item> tItem;  // Tabla que muestra una lista de objetos de la clase Item.

    // Lista observable para contener los elementos (detalles de pedidos) que se mostrarán en la tabla.
    private ObservableList<Item> observableListItem;
    // Instancia de ItemDAO para acceder a operaciones de base de datos relacionadas con Item.
    private ItemDAO itemDAO = new ItemDAO();

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
            // Obtiene y devuelve el valor de id_item como una propiedad simple de cadena.
            String id_item = String.valueOf(fila.getValue().getId_item());
            return new SimpleStringProperty(id_item);
        });

        cCodigoPedido.setCellValueFactory((fila) -> {
            // Obtiene y devuelve el valor del código del pedido como una propiedad simple de cadena.
            String codigo_pedido = String.valueOf(fila.getValue().getCodigo_pedido().getCodigo_pedido());
            return new SimpleStringProperty(codigo_pedido);
        });

        cCantidad.setCellValueFactory((fila) -> {
            // Obtiene y devuelve el valor de la cantidad como una propiedad simple de cadena.
            String cantidad = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(cantidad);
        });

        cProducto.setCellValueFactory((fila) -> {
            // Obtiene y devuelve el valor del producto y su precio formateado como una propiedad simple de cadena.
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
        // Establece el usuario en la sesión como null, indicando que no hay usuario activo.
        Sesion.setUsuario(null);

        // Carga el FXML de la pantalla de inicio de sesión utilizando el método loadFXMLLogin de la clase HelloApplication.
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
        // Carga el FXML de la vista de usuario utilizando el método loadFXMLUser de la clase HelloApplication.
        HelloApplication.loadFXMLUser("user-view.fxml");
    }

    /**
     * Muestra información acerca del creador de la aplicación.
     *
     * @param actionEvent Evento de acción que desencadena la visualización de la información.
     */
    @Deprecated
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


    /**
     * Añade un nuevo ítem al pedido.
     *
     * @param actionEvent Evento de acción que desencadena la adición de un nuevo ítem.
     */
    @javafx.fxml.FXML
    public void addItem(ActionEvent actionEvent) {
        // Crea una nueva instancia de la clase Item.
        var item = new Item();
        // Establece el nuevo item en la sesión.
        Sesion.setItem(item);
        // Carga el FXML de la vista de item utilizando el método loadFXMLItem de la clase HelloApplication.
        HelloApplication.loadFXMLItem("item-view.fxml");
    }


    /**
     * Elimina un ítem del pedido.
     *
     * @param actionEvent Evento de acción que desencadena la eliminación de un ítem.
     */
    @javafx.fxml.FXML
    public void deleteItem(ActionEvent actionEvent) {
        // Obtiene el item seleccionado de la tabla (tItem)
        Item itemSeleccionado = tItem.getSelectionModel().getSelectedItem();

        // Verifica si se seleccionó un item
        if (itemSeleccionado != null) {
            // Crea un cuadro de diálogo de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿De verdad que quieres borrar el item: " + itemSeleccionado.getId_item() +
                    " del producto " + itemSeleccionado.getProducto().getNombre() + "?");

            // Muestra el cuadro de diálogo y espera la respuesta del usuario
            var result = alert.showAndWait().get();

            // Verifica si el usuario hizo clic en "OK" en el cuadro de diálogo de confirmación
            if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                // Elimina el item de la base de datos utilizando itemDAO
                itemDAO.delete(itemSeleccionado);

                // Elimina el item de la lista observable asociada a la tabla
                observableListItem.remove(itemSeleccionado);

                // Calcula el nuevo total del pedido restando el precio del item eliminado
                Pedido pedidoActual = Sesion.getPedido();
                Double nuevoTotal = calcularTotal(pedidoActual) - (itemSeleccionado.getProducto().getPrecio() *
                        itemSeleccionado.getCantidad());
                System.out.println(nuevoTotal);

                // Actualiza el total del pedido en el objeto pedidoActual
                pedidoActual.setTotal(nuevoTotal);

                // Actualiza el total del pedido en la base de datos utilizando PedidoDAO
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.update(pedidoActual);
            }
        } else {
            // Muestra un mensaje de advertencia si no se ha seleccionado ningún item para eliminar
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
        // Inicializa la variable total a 0.0
        Double total = 0.0;
        // Itera sobre los elementos (Items) en la lista de items del pedido actual
        for (Item item : pedidoActual.getItems()) {
            // Para cada elemento, suma el precio del producto multiplicado por la cantidad al total
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        // Devuelve el total calculado
        return total;
    }


    /**
     * Actualiza la información del pedido en la base de datos.
     */
    private void actualizarPedido() {
        // Obtiene el pedido actual de la sesión (asumiendo que hay una clase Sesion con un método getPedido())
        Pedido pedidoActual = Sesion.getPedido();
        // Calcula el total del pedido actual usando un método calcularTotal() (que no se proporciona en el código)
        Double totalActual = calcularTotal(pedidoActual);
        // Establece el total calculado en el objeto Pedido actual
        pedidoActual.setTotal(totalActual);
        try {
            // Crea una instancia de la clase PedidoDAO
            PedidoDAO pedidoDAO = new PedidoDAO();

            // Actualiza el pedido actual en la base de datos utilizando el método update() de PedidoDAO
            pedidoDAO.update(pedidoActual);
        } catch (Exception ex) {
            // Captura cualquier excepción que pueda ocurrir durante la actualización y la imprime en la consola
            ex.printStackTrace();
        }
    }

    /**
     * Genera y muestra un informe PDF basado en el código del pedido actual.
     *
     * @param actionEvent Evento de acción que desencadenó la generación del informe.
     */
    @javafx.fxml.FXML
    public void printPDF(ActionEvent actionEvent) {
        // Obtengo el código del pedido desde la sesión
        String codigo_pedido = Sesion.getPedido().getCodigo_pedido();
        // Creo una nueva ventana
        Stage stage = new Stage();
        System.out.println(codigo_pedido);

        try {
            // Estableco la conexión con la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestor_pedidos","root","");

            // Creo un mapa parametrizado para el informe
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("codigo_pedido", codigo_pedido);// Este parametro es el que luego voy a añadir en JaspersoftReport a mano y le añado un valor por defecto

            // Relleno el informe empleando JasperReports
            JasperPrint jasperPrint = JasperFillManager.fillReport("ListaPedidos.jasper", hashMap, connection);

            // Creo un componente SwingNode para integrar el informe en la interfaz JavaFX
            SwingNode swingNode = new SwingNode();
            swingWindow(swingNode, jasperPrint);

            // Configuro la escena y muestro la ventana
            StackPane root = new StackPane();
            root.getChildren().add(swingNode);
            Scene scene = new Scene(root, 800, 600);
            stage.getIcons().add(new Image(getClass().getResource("/imagenes/logo_gestor_pedidos-removebg-preview.png").toString(), 100, 100, true, true));
            stage.setTitle("details-view.fxml");
            stage.setScene(scene);
            stage.show();

            // Exporto el informe a PDF
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("pedido.pdf"));
            exporter.setConfiguration(new SimplePdfExporterConfiguration());
            exporter.exportReport();
        } catch (SQLException | JRException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Crea y establece un visor Jasper como contenido de un nodo SwingNode de JavaFX de manera asíncrona.
     *
     * @param swingNode    El nodo SwingNode al que se agregará el visor Jasper.
     * @param jasperPrint  El informe JasperPrint que se mostrará en el visor.
     */
    private void swingWindow(final SwingNode swingNode, JasperPrint jasperPrint) {
        SwingUtilities.invokeLater(() -> {
            //Creo un visor Jasper y lo establezco como contenido del nodo Swing
            JRViewer viewer = new JRViewer(jasperPrint);
            swingNode.setContent(viewer);
        });
    }
}