package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;

import com.example.gestordepedidoshibernate.domain.excepciones.ErrorAccesoException;
import com.example.gestordepedidoshibernate.domain.sesion.Sesion;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;
import com.example.gestordepedidoshibernate.domain.usuario.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de inicio de sesión.
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;  // Campo de texto para ingresar el nombre de usuario.

    @FXML
    private PasswordField txtPassword;  // Campo de texto para ingresar la contraseña (oculta por puntos o asteriscos).
    @FXML
    private Button btnAcceder;
    @FXML
    private Label labelInfo;


    /**
     * Método para manejar el evento de inicio de sesión.
     *
     * @param actionEvent Evento de acción que desencadena el intento de inicio de sesión.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        // Obtiene el nombre de usuario (o correo electrónico) y la contraseña desde los campos de texto.
        String usuarioEmail = txtUsuario.getText();
        String password = txtPassword.getText();
        Usuario usuario = null;

        try {
            // Intenta validar el usuario utilizando un objeto UsuarioDAO y los datos proporcionados.
            usuario = new UsuarioDAO().validateUser(usuarioEmail, password);

            // Establece el usuario en la sesión.
            Sesion.setUsuario(usuario);

            // Muestra un cuadro de diálogo de confirmación para indicar que la sesión se inició correctamente.
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bienvenido");
            alert.setHeaderText("Se ha iniciado correctamente la sesión");
            alert.setContentText("Bienvenido - " + usuario.getNombre());
            alert.showAndWait();

            // Carga la vista de usuario.
            HelloApplication.loadFXMLUser("user-view.fxml");

        } catch (ErrorAccesoException e) {
            // Si ocurre un error debido a un usuario inexistente o una contraseña incorrecta, muestra un mensaje de
            // advertencia.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("¡Algo ha fallado!");
            alert.setContentText("El usuario no existe o la contraseña es incorrecta.");
            alert.showAndWait();
            System.out.println("Usuario no encontrado.");
        } catch (IOException e) {
            // Lanza una excepción RuntimeException si ocurre un error al cargar la vista de usuario.
            throw new RuntimeException(e);
        }
    }


    /**
     * Método que se llama automáticamente al cargar la interfaz gráfica.
     *
     * @param url            La ubicación del archivo FXML.
     * @param resourceBundle Recursos específicos del idioma.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Aquí se pueden realizar inicializaciones en caso de ser necesario.
    }
}