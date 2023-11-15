package com.example.gestordepedidoshibernate.domain.conexionbbdd;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
/**
 * Esta clase se encarga de establecer la conexión con la base de datos MySQL utilizando la configuración proporcionada
 * en el archivo "bbdd.properties".
 * La conexión se realiza al cargarse la clase, y la instancia de la conexión está disponible a través del campo
 * estático "connection".
 */
public class DBConnection {
    @Getter
    private static Connection connection; // Conexión a la base de datos.

    private static Logger logger; // Registro de eventos.

    static {
        logger = Logger.getLogger(DBConnection.class.getName());
        String url;
        String user;
        String pass;
        Properties properties = new Properties();
        try {
            InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("bbdd.properties");
            properties.load(inputStream);

            // Construyo la URL de conexión con la información del archivo de propiedades.
            url = "jdbc:mysql://" + properties.get("host") + ":" + properties.get("port") + "/"
                    + properties.get("dbname");
            user = (String) properties.get("user");
            pass = (String) properties.get("pass");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // Establezco la conexión a la base de datos.
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión realizada con éxito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
