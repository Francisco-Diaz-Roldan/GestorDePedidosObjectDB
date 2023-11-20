package com.example.gestordepedidoshibernate.domain.producto;


import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import com.example.gestordepedidoshibernate.domain.pedido.Pedido;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ProductoDAO implements DAO<Producto> {
    @Override
    public ArrayList<Producto> getAll() {
        var salida = new ArrayList<Producto>(0);

        try(Session s = HibernateUtils.getSessionFactory().openSession()){
            Query<Producto> q = s.createQuery("from Producto",Producto.class);
            salida = (ArrayList<Producto>) q.getResultList();
        }

        return salida;
    }

    @Override
    public Producto get(Integer id) {
        return null;
    }

    @Override
    public Producto save(Producto data) {
        return null;
    }

    @Override
    public void update(Producto data) {

    }

    @Override
    public void delete(Producto data) {

    }


    //public Producto loadProducto(Integer id_producto);
}
