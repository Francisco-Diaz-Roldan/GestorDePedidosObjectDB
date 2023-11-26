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

public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;


    @FXML
    public void login(ActionEvent actionEvent) {
        String usuarioEmail = txtUsuario.getText();
        String password = txtPassword.getText();
        Usuario usuario = null;

        try {
            usuario = (new UsuarioDAO().validateUser(usuarioEmail, password));
            Sesion.setUsuario(usuario);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bienvenido");
            alert.setHeaderText("Se ha iniciado correctamente la sesión");
            alert.setContentText("Bienvenido - " + usuario.getNombre());
            alert.showAndWait();

            HelloApplication.loadFXMLUser("user-view.fxml");

        } catch (ErrorAccesoException e) {
            // Si ocurre un error debido a un usuario inexistente, muestra un mensaje de advertencia.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("¡Algo ha fallado!");
            alert.setContentText("El usuario o no existe o no corresponde con la contraseña.");
            alert.showAndWait();
            System.out.println("Usuario no encontrado.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
