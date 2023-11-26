package com.example.gestordepedidoshibernate.domain.pedido;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Clase que implementa la interfaz DAO para la entidad Pedido. Proporciona métodos para acceder y manipular los datos
 * asociados a los pedidos en la base de datos.
 */
public class PedidoDAO implements DAO<Pedido> {

    /**
     * Recupera todos los pedidos almacenados en la base de datos.
     *
     * @return Lista de todos los pedidos.
     */
    @Override
    public ArrayList<Pedido> getAll() {
        var salida = new ArrayList<Pedido>(0);

        try (Session s = HibernateUtils.getSessionFactory().openSession()) {
            Query<Pedido> q = s.createQuery("from Pedido", Pedido.class);
            salida = (ArrayList<Pedido>) q.getResultList();
        }

        return salida;
    }

    /**
     * Recupera un pedido específico de la base de datos según su identificador único.
     *
     * @param id Identificador único del pedido.
     * @return Pedido recuperado de la base de datos.
     */
    @Override
    public Pedido get(Integer id) {
        var salida = new Pedido();
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            salida = session.get(Pedido.class, id);
        }
        return salida;
    }

    /**
     * Guarda un nuevo pedido o actualiza uno existente en la base de datos.
     *
     * @param data Pedido a ser guardado o actualizado.
     * @return Pedido guardado o actualizado.
     */
    @Override
    public Pedido save(Pedido data) {
        try (Session s = HibernateUtils.getSessionFactory().openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();
                s.save(data);
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
     * Actualiza un pedido existente en la base de datos.
     *
     * @param data Pedido a ser actualizado.
     */
    @Override
    public void update(Pedido data) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Da comienzo la transacción
            transaction = session.beginTransaction();

            // Actualizo el pedido en la base de datos
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
     * Elimina un pedido de la base de datos.
     *
     * @param data Pedido a ser eliminado.
     */
    @Override
    public void delete(Pedido data) {
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            Pedido pedido = session.get(Pedido.class, data.getId_pedido());
            session.remove(pedido);
        });
    }
}
