package com.example.gestordepedidoshibernate.domain.hibernateutils;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log
public class HibernateUtils {
    private static SessionFactory sf;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sf = configuration.buildSessionFactory();

            log.info("¡SessionFactory creada con exito!");
        } catch(Exception e){
            log.severe("Error al inicializar la SessionFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
