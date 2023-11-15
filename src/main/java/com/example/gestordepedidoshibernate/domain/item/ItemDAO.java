package com.example.gestordepedidoshibernate.domain.item;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import com.example.gestordepedidoshibernate.domain.usuario.Usuario;
import org.hibernate.Session;
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
        return null;//TODO hacer el save
    }

    @Override
    public void update(Item data) {

    }

    @Override
    public void delete(Item data) {

    }
    //public ArrayList<Item> loadAll(String codigo_pedido);

}
