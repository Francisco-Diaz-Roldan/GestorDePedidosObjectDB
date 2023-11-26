package com.example.gestordepedidoshibernate.domain.item;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;


/**
 * Clase que implementa la interfaz DAO para la entidad Item, proporcionando métodos para acceder y manipular datos
 * relacionados con los items en la base de datos.
 */
public class ItemDAO implements DAO<Item> {

    /**
     * Obtiene todos los items de la base de datos.
     *
     * @return Una lista de todos los items en la base de datos.
     */
    @Override
    public ArrayList<Item> getAll() {
        // Crea una lista para almacenar los resultados.
        var salida = new ArrayList<Item>(0);

        try (Session sesion = HibernateUtils.getSessionFactory().openSession()) {
            // Abre una sesión de Hibernate.

            // Crea una consulta HQL (Hibernate Query Language) para obtener todos los objetos Item.
            Query<Item> q = sesion.createQuery("from Item", Item.class);

            // Ejecuta la consulta y asigna los resultados a la lista de salida.
            salida = (ArrayList<Item>) q.getResultList();
        }

        // Retorna la lista de resultados.
        return salida;
    }


    /**
     * Obtiene un item de la base de datos por su identificador único.
     *
     * @param id El identificador único del item.
     * @return El item correspondiente al identificador dado.
     */
    @Override
    public Item get(Integer id) {
        // Crea una instancia de Item para almacenar el resultado.
        var salida = new Item();

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Abre una sesión de Hibernate.

            // Utiliza el método `get` de Hibernate para obtener el objeto Item por su identificador.
            salida = session.get(Item.class, id);
        }

        // Retorna el objeto Item obtenido por su identificador.
        return salida;
    }


    /**
     * Guarda un nuevo item o actualiza uno existente en la base de datos.
     *
     * @param data El item a guardar o actualizar.
     * @return El item guardado o actualizado.
     */
    @Override
    public Item save(Item data) {
        // Abre una sesión de Hibernate.
        try (Session s = HibernateUtils.getSessionFactory().openSession()) {
            // Inicia una transacción.
            Transaction t = null;
            try {
                t = s.beginTransaction();

                // Guarda el objeto Item en la base de datos.
                s.persist(data);

                // Confirma la transacción.
                t.commit();
            } catch (Exception e) {
                // En caso de excepción, realiza un rollback de la transacción.
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
            // Retorna el objeto Item.
            return data;
        }
    }


    /**
     * Actualiza un item existente en la base de datos.
     *
     * @param data El item a actualizar.
     */
    @Override
    public void update(Item data) {
        // Abre una sesión de Hibernate.
        Session session = HibernateUtils.getSessionFactory().openSession();

        // Inicializa la transacción.
        Transaction transaction = null;

        try {
            // Da comienzo la transacción.
            transaction = session.beginTransaction();

            // Actualiza el objeto Item en la base de datos.
            session.update(data);

            // Confirma la transacción.
            transaction.commit();
        } catch (Exception e) {
            // En caso de excepción, realiza un rollback de la transacción.
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate.
            session.close();
        }
    }


    /**
     * Elimina un item de la base de datos.
     *
     * @param data El item a eliminar.
     */
    @Override
    public void delete(Item data) {
        // Utiliza el método inTransaction de HibernateUtils para trabajar con una transacción.
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            // Obtiene el objeto Item a partir de su identificador.
            Item item = session.get(Item.class, data.getId_item());

            // Elimina el objeto Item de la base de datos.
            session.remove(item);
        });
    }

}
