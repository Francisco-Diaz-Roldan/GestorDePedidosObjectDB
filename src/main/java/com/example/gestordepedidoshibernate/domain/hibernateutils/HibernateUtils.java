package com.example.gestordepedidoshibernate.domain.hibernateutils;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase de utilidad para gestionar la creación y obtención de la {@code SessionFactory} de Hibernate.
 *
 * <p>Esta clase utiliza la anotación {@code @Log} de Lombok para generar un logger estático.</p>
 */
@Log
public class HibernateUtils {

    /**
     * Session Factory de Hibernate.
     */
    private static SessionFactory sf;

    /**
     * Inicializador estático que configura y crea la {@code SessionFactory} de Hibernate.
     */
    static {
        try {
            // Crea una instancia de Configuration, que se utiliza para configurar Hibernate.
            Configuration configuration = new Configuration();

            // Carga la configuración de Hibernate desde el archivo hibernate.cfg.xml (por convención).
            configuration.configure();

            // Construye la SessionFactory utilizando la configuración proporcionada.
            sf = configuration.buildSessionFactory();

            // Imprime un mensaje de éxito en el log.
            log.info("¡SessionFactory creada con éxito!");
        } catch(Exception e){
            // Imprime un mensaje de error en el log si hay algún problema al crear la SessionFactory.
            log.severe("Error al inicializar la SessionFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la instancia única de la {@code SessionFactory}.
     *
     * @return La {@code SessionFactory} de Hibernate.
     */
    public static SessionFactory getSessionFactory(){
        return sf;
    }
}