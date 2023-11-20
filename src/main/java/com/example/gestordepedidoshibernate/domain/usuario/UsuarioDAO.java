package com.example.gestordepedidoshibernate.domain.usuario;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UsuarioDAO implements DAO<Usuario> {
    //public Usuario loadUser(String email, String password) throws UsuarioIncorrectoException, PasswordIncorrectaException;

    @Override
    public ArrayList<Usuario> getAll() {
        var salida = new ArrayList<Usuario>(0);

        try(Session s = HibernateUtils.getSessionFactory().openSession()){
            Query<Usuario> q = s.createQuery("from Usuario ",Usuario.class);
            salida = (ArrayList<Usuario>) q.getResultList();
        }

        return salida;
    }

    @Override
    public Usuario get(Integer id) {
        var salida = new Usuario();
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            salida = session.get(Usuario.class, id);
        }
        return salida;
    }

    @Override
    public Usuario save(Usuario data) {
        return null;
    }

    @Override
    public void update(Usuario data) {

    }

    @Override
    public void delete(Usuario data) {

    }
    public Usuario validateUser(String email, String pass) {
        Usuario result = null;

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Usuario> q = session.createQuery("from Usuario where email=:e and pass=:p", Usuario.class);
            q.setParameter("e", email);
            q.setParameter("p", pass);

            try {
                result = q.getSingleResult();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }
}
