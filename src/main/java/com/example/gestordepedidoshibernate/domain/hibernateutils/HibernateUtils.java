package com.example.gestordepedidoshibernate.domain.hibernateutils;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log
public class HibernateUtils {
    private static SessionFactory sf;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sf = cfg.buildSessionFactory();
            log.info("¡SessionFactory creada con exito!");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
