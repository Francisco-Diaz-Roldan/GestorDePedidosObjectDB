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
        var salida = new ArrayList<Item>(0);
        try (Session sesion = HibernateUtils.getSessionFactory().openSession()) {
            Query<Item> q = sesion.createQuery("from Item", Item.class);
            salida = (ArrayList<Item>) q.getResultList();
        }
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
        var salida = new Item();
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            salida = session.get(Item.class, id);
        }
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
        try (Session s = HibernateUtils.getSessionFactory().openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();
                s.persist(data);
                t.commit();
            } catch (Exception e) {
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
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
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Da comienzo la transacción
            transaction = session.beginTransaction();

            // Actualizo el item en la base de datos
            session.update(data);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
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
        // Implementa la lógica para eliminar un item de la base de datos si es necesario.
    }
}
