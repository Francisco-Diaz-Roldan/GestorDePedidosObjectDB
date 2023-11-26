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
        // Crea una lista para almacenar los resultados.
        var salida = new ArrayList<Pedido>(0);

        // Abre una sesión de Hibernate.
        try (Session s = HibernateUtils.getSessionFactory().openSession()) {
            // Crea una consulta HQL (Hibernate Query Language) para obtener todos los objetos Pedido.
            Query<Pedido> q = s.createQuery("from Pedido", Pedido.class);

            // Ejecuta la consulta y asigna los resultados a la lista de salida.
            salida = (ArrayList<Pedido>) q.getResultList();
        }

        // Retorna la lista de resultados.
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
        // Crea una instancia de Pedido para almacenar el resultado.
        var salida = new Pedido();

        // Abre una sesión de Hibernate.
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Utiliza el método `get` de Hibernate para obtener el objeto Pedido por su identificador.
            salida = session.get(Pedido.class, id);
        }

        // Retorna el objeto Pedido obtenido por su identificador.
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
        // Abre una sesión de Hibernate.
        try (Session s = HibernateUtils.getSessionFactory().openSession()) {
            // Inicia una transacción.
            Transaction t = null;
            try {
                t = s.beginTransaction();

                // Guarda el objeto Pedido en la base de datos.
                s.save(data);

                // Confirma la transacción.
                t.commit();
            } catch (Exception e) {
                // En caso de excepción, realiza un rollback de la transacción.
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
            // Devuelve el objeto Pedido.
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
        // Abre una sesión de Hibernate.
        Session session = HibernateUtils.getSessionFactory().openSession();

        // Inicializa la transacción.
        Transaction transaction = null;

        try {
            // Da comienzo la transacción.
            transaction = session.beginTransaction();

            // Utiliza el método `update` de Hibernate para actualizar el objeto Pedido en la base de datos.
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
     * Elimina un pedido de la base de datos.
     *
     * @param data Pedido a ser eliminado.
     */
    @Override
    public void delete(Pedido data) {
        // Utiliza el método inTransaction de HibernateUtils para trabajar con una transacción.
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            // Obtiene el objeto Pedido a partir de su identificador.
            Pedido pedido = session.get(Pedido.class, data.getId_pedido());
            // Elimina el objeto Pedido de la base de datos.
            session.remove(pedido);
        });
    }

}
