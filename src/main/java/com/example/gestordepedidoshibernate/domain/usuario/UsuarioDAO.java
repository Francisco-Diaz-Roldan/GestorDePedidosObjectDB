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

        // Abre una sesión de Hibernate.
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Crea una consulta HQL (Hibernate Query Language) para obtener todos los registros de Usuario.
            Query<Usuario> q = session.createQuery("from Usuario", Usuario.class);

            // Obtiene la lista de resultados.
            salida = (ArrayList<Usuario>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retorna la lista de Usuarios obtenida de la base de datos.
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
        // Crea una sesión de Hibernate.
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Utiliza el método `get` de Hibernate para obtener el objeto Usuario por su identificador.
            salida = session.get(Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devuelve el objeto Usuario obtenido por su identificador.
        return salida;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     * @param data Usuario a guardar.
     * @return Usuario guardado.
     */
    @Override
    public Usuario save(Usuario data) {
        return null; // Implementa la lógica de guardado si es necesario.
    }

    /**
     * Actualiza la información de un usuario en la base de datos.
     * @param data Usuario con la información actualizada.
     */
    @Override
    public void update(Usuario data) {// Implementa la lógica de actualización si es necesario.
         }

    /**
     * Elimina un usuario de la base de datos.
     * @param data Usuario a eliminar.
     */
    @Override
    public void delete(Usuario data) {
        // Implementa la lógica de eliminación si es necesario.
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

        // Verifica si la SessionFactory está inicializada.
        if (HibernateUtils.getSessionFactory() == null) {
            throw new ErrorAccesoException("Error en la inicialización de la SessionFactory");
        }

        // Abre una sesión de Hibernate.
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // Crea una consulta HQL (Hibernate Query Language) para obtener un usuario por email y contraseña.
            Query<Usuario> q = session.createQuery("from Usuario where email=:e and pass=:p", Usuario.class);
            q.setParameter("e", email);
            q.setParameter("p", pass);

            // Intenta obtener un resultado único.
            try {
                result = q.getSingleResult();
            } catch (NoResultException ex) {
                // En caso de que no se encuentre ningún usuario con la combinación de email y contraseña.
                throw new ErrorAccesoException("Usuario no encontrado");
            }
        }

        // Devuelve el resultado (puede ser null si no se encuentra un usuario).
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

