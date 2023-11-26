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
     * Factoría de sesiones de Hibernate.
     */
    private static SessionFactory sf;

    /**
     * Inicializador estático que configura y crea la {@code SessionFactory} de Hibernate.
     */
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sf = configuration.buildSessionFactory();

            log.info("¡SessionFactory creada con éxito!");
        } catch(Exception e){
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