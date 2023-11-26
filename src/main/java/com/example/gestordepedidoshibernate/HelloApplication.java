package com.example.gestordepedidoshibernate;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


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
    @Override
    public void start(Stage stage) throws IOException {
        miStage = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Gestor de pedidos");
        //stage.getIcons().add(new Image("C:\\Users\\pacod\\IdeaProjects\\GestorDePedidosHibernate\\src\\main\\resources\\imagenes\\logo_gestor_pedidos-removebg-preview.png",100,100, true, true));
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
        Scene scene = new Scene(fxmlLoader.load(), 850, 650);
        miStage.setScene(scene);
    }

    /**
     * Método estático para cargar una escena de detalles en la ventana principal.
     *
     * @param escena El nombre del archivo FXML que representa la escena de detalles a cargar.
     */
    public static void loadFXMLDetails(String escena) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
            Scene scene = new Scene(fxmlLoader.load(), 650, 425);
            miStage.setScene(scene);
        } catch (IOException e) {
            // En caso de error, se lanza una excepción RuntimeException.
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
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
            Scene scene = new Scene(fxmlLoader.load(), 625, 425);
            miStage.setScene(scene);
        } catch (IOException e) {
            // En caso de error, se lanza una excepción RuntimeException.
            throw new RuntimeException(e);
        }
    }
    public static void loadFXMLItem(String escena) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(escena));
            Scene scene = new Scene(fxmlLoader.load(), 625, 425);
            miStage.setScene(scene);
        } catch (IOException e) {
            // En caso de error, se lanza una excepción RuntimeException.
            throw new RuntimeException(e);
        }
    }

    /**
     * Método principal que inicia la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        launch();
    }
}