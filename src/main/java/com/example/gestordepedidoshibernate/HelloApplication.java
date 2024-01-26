package com.example.gestordepedidoshibernate;

import com.example.gestordepedidoshibernate.Data.Data;
import com.example.gestordepedidoshibernate.domain.producto.Producto;
import com.example.gestordepedidoshibernate.domain.producto.ProductoDAO;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;
import com.example.gestordepedidoshibernate.domain.usuario.UsuarioDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * La clase HelloApplication extiende la clase Application de JavaFX y sirve como punto de entrada para la aplicación.
 * Esta clase se encarga de la gestión de ventanas y escenas en la aplicación JavaFX.
 */
public class HelloApplication extends Application {
    private static Stage miStage;

    /**
     * Método start que inicia la aplicación JavaFX y muestra la ventana principal con la escena de inicio.
     *
     * @param stage El objeto Stage que representa la ventana principal de la aplicación.
     * @throws IOException En caso de que ocurra un error de lectura de archivos FXML.
     */
    public void start(Stage stage) throws IOException {

        try {
            // Creo una instancia del UsuarioDAO para interactuar con la entidad Usuario en la base de datos
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Obtengo todos los usuarios de la base de datos
            List<Usuario> usuarios = usuarioDAO.getAll();

            // Compruebo si no hay usuarios en la base de datos
            if (usuarios.isEmpty()) {
                // Genero una lista de usuarios utilizando el método generarUsuarios de la clase Data
                usuarios = Data.generarUsuarios();

                // Guardo todos los usuarios generados en la base de datos
                usuarioDAO.saveAll(usuarios);
            }

            // Creo una instancia del ProductoDAO para interactuar con la entidad Producto en la base de datos
            ProductoDAO productoDAO = new ProductoDAO();

            // Obtengo todos los productos de la base de datos
            List<Producto> productos = productoDAO.getAll();

            // Compruebo si no hay productos en la base de datos
            if (productos.isEmpty()) {
                // Genero una lista de productos utilizando el método generarProductos de la clase Data
                productos = Data.generarProductos();

                // Guardo todos los productos generados en la base de datos
                productoDAO.saveAll(productos);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Establece el stage (escenario) y evita que sea redimensionable.
        miStage = stage;
        stage.setResizable(false);
        // Carga el archivo FXML (interfaz de usuario) utilizando un FXMLLoader.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        // Crea una escena con la interfaz de usuario cargada y establece el tamaño de la ventana.
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        // Configura el título de la ventana.
        stage.setTitle("Gestor de pedidos");
        // Agrega un ícono a la ventana.
        stage.getIcons().add(new Image(getClass().getResource("/imagenes/logo_gestor_pedidos-removebg-preview.png").toString(), 100, 100, true, true));
        // Establece la escena en el escenario y muestra la ventana.
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Método estático para cargar una nueva escena en la ventana principal.
     *
     * @param escena El nombre del archivo FXML que representa la escena a cargar.
     * @throws IOException En caso de que ocurra un error de lectura de archivos FXML.
     */
    public static void loadFXMLUser(String escena) throws IOException {
        // Crea un FXMLLoader para cargar la nueva escena desde el archivo FXML.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
        // Crea una nueva escena con la interfaz de usuario cargada y establece su tamaño.
        Scene scene = new Scene(fxmlLoader.load(), 850, 650);
        // Establece la nueva escena en el mismo Stage (escenario).
        miStage.setScene(scene);
    }


    /**
     * Método estático para cargar una escena de detalles en la ventana principal.
     *
     * @param escena El nombre del archivo FXML que representa la escena de detalles a cargar.
     */
    public static void loadFXMLDetails(String escena) {
        try {
            // Crea un FXMLLoader para cargar la nueva escena desde el archivo FXML.
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
            // Crea una nueva escena con la interfaz de usuario cargada y establece su tamaño.
            Scene scene = new Scene(fxmlLoader.load(), 650, 425);
            // Establece la nueva escena en el mismo Stage (escenario).
            miStage.setScene(scene);
        } catch (IOException e) {
            // En caso de error al cargar el archivo FXML, se lanza una excepción RuntimeException.
            throw new RuntimeException(e);
        }
    }


    /**
     * Método estático para cargar la escena de inicio de sesión en la ventana principal.
     *
     * @param escena El nombre del archivo FXML que representa la escena de inicio de sesión a cargar.
     */
    public static void loadFXMLLogin(String escena) {
        try {
            // Crea un FXMLLoader para cargar la nueva escena desde el archivo FXML.
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
            // Crea una nueva escena con la interfaz de usuario cargada y establece su tamaño.
            Scene scene = new Scene(fxmlLoader.load(), 625, 425);
            // Establece la nueva escena en el mismo Stage (escenario).
            miStage.setScene(scene);
        } catch (IOException e) {
            // En caso de error al cargar el archivo FXML, se lanza una excepción RuntimeException.
            throw new RuntimeException(e);
        }
    }


    /**
     * Método estático para cargar una escena de items en la ventana principal.
     *
     * @param escena El nombre del archivo FXML que representa la escena de items a cargar.
     */
    public static void loadFXMLItem(String escena) {
        try {
            // Crea un FXMLLoader para cargar la nueva escena desde el archivo FXML.
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
            // Crea una nueva escena con la interfaz de usuario cargada y establece su tamaño.
            Scene scene = new Scene(fxmlLoader.load(), 625, 425);
            // Establece la nueva escena en el mismo Stage (escenario).
            miStage.setScene(scene);
        } catch (IOException e) {
            // En caso de error al cargar el archivo FXML, se lanza una excepción RuntimeException.
            throw new RuntimeException(e);
        }
    }


    /**
     * Método principal que inicia la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        // Llama al método launch(), que inicia la aplicación JavaFX.
        launch();
    }
}
