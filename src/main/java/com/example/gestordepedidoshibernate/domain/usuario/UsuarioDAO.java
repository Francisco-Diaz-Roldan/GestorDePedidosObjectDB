package com.example.gestordepedidoshibernate.domain.usuario;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.excepciones.ErrorAccesoException;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Clase que implementa operaciones de acceso a datos (DAO) para la entidad Usuario.
 */
public class UsuarioDAO implements DAO<Usuario> {

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     * @return Lista de usuarios.
     */
    @Override
    public ArrayList<Usuario> getAll() {
        var salida = new ArrayList<Usuario>(0);

        try (Session s = HibernateUtils.getSessionFactory().openSession()) {
            Query<Usuario> q = s.createQuery("from Usuario ", Usuario.class);
            salida = (ArrayList<Usuario>) q.getResultList();
        }
        return salida;
    }

    /**
     * Obtiene un usuario por su identificador único.
     * @param id Identificador único del usuario.
     * @return Usuario encontrado o un objeto Usuario vacío si no se encuentra.
     */
    @Override
    public Usuario get(Integer id) {
        var salida = new Usuario();
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            salida = session.get(Usuario.class, id);
        }
        return salida;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     * @param data Usuario a guardar.
     * @return Usuario guardado.
     */
    @Override
    public Usuario save(Usuario data) {
        return null; // Implementar lógica de guardado si es necesario.
    }

    /**
     * Actualiza la información de un usuario en la base de datos.
     * @param data Usuario con la información actualizada.
     */
    @Override
    public void update(Usuario data) {
        // Implementar lógica de actualización si es necesario.
    }

    /**
     * Elimina un usuario de la base de datos.
     * @param data Usuario a eliminar.
     */
    @Override
    public void delete(Usuario data) {
        // Implementar lógica de eliminación si es necesario.
    }

    /**
     * Valida las credenciales de un usuario.
     * @param email Correo electrónico del usuario.
     * @param pass Contraseña del usuario.
     * @return Usuario validado.
     * @throws ErrorAccesoException Excepción lanzada en caso de error de acceso.
     */
    public Usuario validateUser(String email, String pass) throws ErrorAccesoException {
        Usuario result = null;

        if (HibernateUtils.getSessionFactory() == null) {
            throw new ErrorAccesoException("Error en la inicialización de la SessionFactory");
        }
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<Usuario> q = session.createQuery("from Usuario where email=:e and pass=:p", Usuario.class);
            q.setParameter("e", email);
            q.setParameter("p", pass);

            try {
                result = q.getSingleResult();
            } catch (NoResultException ex) {
                // En caso de que no se encuentre ningún usuario con la combinación de email y contraseña.
                throw new ErrorAccesoException("Usuario no encontrado");
            }
        }
        return result;
    }
}
    /*public Usuario validateUser(String email, String pass) {
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
    }*/

