package com.example.gestordepedidos;

import com.example.gestordepedidos.domain.conexionbbdd.DBConnection;
import com.example.gestordepedidos.domain.excepciones.PasswordIncorrectaException;
import com.example.gestordepedidos.domain.excepciones.UsuarioIncorrectoException;
import com.example.gestordepedidos.domain.usuario.UsuarioDAOImp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    private static Stage miStage;

    @Override
    public void start(Stage stage) throws IOException {
        miStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Gestor de pedidos");
        stage.setScene(scene);
        stage.show();
    }

    public static void loadFXMLUser(String escena) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
        Scene scene = new Scene(fxmlLoader.load(), 850, 650);
        miStage.setScene(scene);
    }

    public static void loadFXMLDetails(String escena) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
            Scene scene = new Scene(fxmlLoader.load(), 650, 425);
            miStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFXMLLogin(String escena) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
            Scene scene = new Scene(fxmlLoader.load(), 625, 425);
            miStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main (String[]args){
        launch();
    }
}