package com.example.gestordepedidoshibernate.domain.pedido;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import com.example.gestordepedidoshibernate.domain.item.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PedidoDAO implements DAO<Pedido> {
    @Override
    public ArrayList<Pedido> getAll() {
        var salida = new ArrayList<Pedido>(0);

        try(Session s = HibernateUtils.getSessionFactory().openSession()){
            Query<Pedido> q = s.createQuery("from Pedido",Pedido.class);
            salida = (ArrayList<Pedido>) q.getResultList();
        }

        return salida;
    }

    @Override
    public Pedido get(Integer id) {
        var salida = new Pedido();
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            salida = session.get(Pedido.class, id);
        }
        return salida;    }

    @Override
    public Pedido save(Pedido data) {
        try (org.hibernate.Session s = HibernateUtils.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            try {
                s.persist(data);
                t.commit();
            } catch (Exception e) {
                if (t != null && t.isActive()) {
                    t.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }


    @Override
    public void update(Pedido data) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            //Da comienzo la transaccion
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

    @Override
    public void delete(Pedido data) {
        HibernateUtils.getSessionFactory().inTransaction((session) ->{
            Pedido p = session.get( Pedido.class, data.getId_pedido());
            session.remove(p);
        });
    }
    //public ArrayList<Pedido> loadAll(Integer id_pedido);
}
