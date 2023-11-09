package com.example.gestordepedidos.controller;

import com.example.gestordepedidos.HelloApplication;
import com.example.gestordepedidos.domain.conexionbbdd.DBConnection;
import com.example.gestordepedidos.domain.excepciones.PasswordIncorrectaException;
import com.example.gestordepedidos.domain.excepciones.UsuarioIncorrectoException;
import com.example.gestordepedidos.domain.sesion.Sesion;
import com.example.gestordepedidos.domain.usuario.Usuario;
import com.example.gestordepedidos.domain.usuario.UsuarioDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controlador de la vista de inicio de sesión.
 * Esta clase maneja las interacciones de la vista de inicio de sesión, como la validación y la carga de la vista
 * principal del usuario.
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnAcceder;
    @FXML
    private Label labelInfo;

    /**
     * Método que se ejecuta cuando se pulsa el botón "Acceder" en la vista de inicio de sesión.
     * Realiza la validación del usuario y carga la vista principal del usuario si las credenciales son correctas.
     *
     * @param actionEvent Evento de acción que desencadena la llamada a este método.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        // Obtengo el correo del usuario y la contraseña introducidos en los campos de texto.
        String usuarioEmail = txtUsuario.getText();
        String password = txtPassword.getText();

        // Creo una instancia del DAO para acceder a los datos del usuario en la base de datos.
        UsuarioDAOImp usuarioDAOImp = new UsuarioDAOImp(DBConnection.getConnection());

        try {
            // Cargo el usuario utilizando el correo y la contraseña proporcionados.
            Usuario usuario = usuarioDAOImp.loadUser(usuarioEmail, password);

            // Se establece al usuario en la sesión actual.
            Sesion.setUsuario(usuario);

            // Muestro un mensaje de bienvenida al usuario, indicando que ha iniciado correctamente la sesión.
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bienvenido");
            alert.setHeaderText("Se ha iniciado correctamente la sesión");
            alert.setContentText("Bienvenido - " + usuario.getNombre());
            alert.showAndWait();

            // Cargo la vista principal del usuario.
            HelloApplication.loadFXMLUser("user-view.fxml");
        } catch (PasswordIncorrectaException e) {
            // Muestro un mensaje de error si la contraseña es incorrecta.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Contraseña incorrecta");
            alert.setContentText("Contraseña incorrecta");
            alert.showAndWait();

            System.out.println("Contraseña incorrecta");

            labelInfo.setText("Contraseña incorrecta");

        } catch (IOException e) {
            labelInfo.setText("Contraseña incorrecta");
            labelInfo.setStyle("-fx-text-fill: red;");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Contraseña incorrecta");
            alert.setContentText("Contraseña incorrecta");
            alert.showAndWait();

            System.out.println("Contraseña incorrecta");


        } catch (UsuarioIncorrectoException e) {
            labelInfo.setText("Usuario incorrecto");
            labelInfo.setStyle("-fx-text-fill: red;");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Usuario incorrecto");
            alert.setContentText("Usuario incorrecto");
            alert.showAndWait();

            System.out.println(" Usuario incorrecto");


        }
    }

    /**
     * Método de inicialización de JavaFX.
     *
     * @param url            Hace referencia a la ubicación de la raíz del documento a la que se resuelve la URL que se
     *                       le pasa como argumento.
     * @param resourceBundle Se refiere a los recursos de un objeto ResourceBundle para esta inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
