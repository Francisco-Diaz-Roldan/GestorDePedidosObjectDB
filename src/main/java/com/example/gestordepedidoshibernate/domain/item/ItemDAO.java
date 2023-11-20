package com.example.gestordepedidoshibernate.domain.item;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;


public class ItemDAO implements DAO<Item> {
    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        try(Session sesion = HibernateUtils.getSessionFactory().openSession()){
            Query<Item> q = sesion.createQuery("from Item", Item.class);
            salida = (ArrayList<Item>) q.getResultList();
        }
        return salida;
        }

    @Override
    public Item get(Integer id) {
        var salida = new Item();
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            salida = session.get(Item.class, id);
        }
        return salida;
    }

    @Override
    public Item save(Item data) {
        try (org.hibernate.Session s = HibernateUtils.getSessionFactory().openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();
                s.persist(data);//Lo hago persistente por lo que se sincronizan los datos con la base de datos, lo que implica que el objeto se guarda y se modifica
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

    @Override
    public void update(Item data) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            //Da comienzo la transaccion
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

    @Override
    public void delete(Item data) {

    }
    //public ArrayList<Item> loadAll(String codigo_pedido);

}
