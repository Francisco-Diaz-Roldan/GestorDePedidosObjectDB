package com.example.gestordepedidoshibernate.controller;

import com.example.gestordepedidoshibernate.HelloApplication;

import com.example.gestordepedidoshibernate.domain.excepciones.PasswordIncorrectaException;
import com.example.gestordepedidoshibernate.domain.excepciones.UsuarioIncorrectoException;
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
    private Button btnAcceder;
    @FXML
    private Label labelInfo;


    @FXML
    public void login(ActionEvent actionEvent) {
        String usuarioEmail = txtUsuario.getText();
        String password = txtPassword.getText();

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.validateUser(usuarioEmail, password);
            Sesion.setUsuario(usuario);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bienvenido");
            alert.setHeaderText("Se ha iniciado correctamente la sesión");
            alert.setContentText("Bienvenido - " + usuario.getNombre());
            alert.showAndWait();

            HelloApplication.loadFXMLUser("user-view.fxml");

        } catch (PasswordIncorrectaException e) {
            warningException("Contraseña incorrecta");

        } catch (UsuarioIncorrectoException e) {
            warningException("Usuario incorrecto");

        } catch (IOException e) {
            warningException("Error de entrada/salida");
        }
    }

    private void warningException(String mensaje) {
        labelInfo.setText(mensaje);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(mensaje);
        alert.setContentText(mensaje);
        alert.showAndWait();
        System.out.println(mensaje);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
