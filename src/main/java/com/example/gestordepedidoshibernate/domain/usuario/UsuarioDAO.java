package com.example.gestordepedidoshibernate.domain.usuario;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.excepciones.ErrorAccesoException;
import com.example.gestordepedidoshibernate.objectdbutils.ObjectDBUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa operaciones de acceso a datos (DAO) para la entidad Usuario.
 */
public class UsuarioDAO implements DAO<Usuario> {

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     *
     * @return Lista de usuarios.
     */
    @Override
    public ArrayList<Usuario> getAll() {
        var salida = new ArrayList<Usuario>(0);
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();

        // Abro una sesión de Hibernate.
        try {
            TypedQuery<Usuario> query = entityManager.createQuery("select u from Usuario u", Usuario.class);

            // Obtengo la lista de resultados.
            salida = (ArrayList<Usuario>) query.getResultList();
        } finally {
            entityManager.close();
        }

        // Devuelve la lista de Usuarios obtenida de la base de datos.
        return salida;
    }

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id Identificador único del usuario.
     * @return Usuario encontrado o un objeto Usuario vacío si no se encuentra.
     */
    @Override
    public Usuario get(Integer id) {
        Usuario salida = null;
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        // Crea una sesión de Hibernate.
        try {
            TypedQuery<Usuario> query = entityManager.createQuery("select u from Usuario u where  u.id_usuario= :id", Usuario.class);
            query.setParameter("id", id);
            // Utiliza el método `get` de Hibernate para obtener el objeto Usuario por su identificador.
            var resultado = query.getResultList();
            if (!resultado.isEmpty()) {
                salida = resultado.get(0);
            }
        } finally {
            entityManager.close();
        }
        // Devuelve la lista de Usuarios obtenida de la base de datos.
        return salida;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param data Usuario a guardar.
     * @return Usuario guardado.
     */
    @Override
    public Usuario save(Usuario data) {
        return null; // Implementa la lógica de guardado si es necesario.
    }

    /**
     * Actualiza la información de un usuario en la base de datos.
     *
     * @param data Usuario con la información actualizada.
     * @return
     */
    @Override
    public Usuario update(Usuario data) {// Implementa la lógica de actualización si es necesario.
        return null;
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param data Usuario a eliminar.
     */
    @Override
    public void delete(Usuario data) {
        // Implementa la lógica de eliminación si es necesario.
    }

    /**
     * Guarda una lista de objetos de tipo Usuario en la base de datos.
     *
     * @param data La lista de objetos de tipo Usuario que se va a guardar.
     */
    @Override
    public void saveAll(List<Usuario> data) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            for (Usuario u : data) {
                entityManager.persist(u);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Valida las credenciales de un usuario.
     *
     * @param email Correo electrónico del usuario.
     * @param pass  Contraseña del usuario.
     * @return Usuario validado.
     * @throws ErrorAccesoException Excepción lanzada en caso de error de acceso.
     */
    public Usuario validateUser(String email, String pass) throws ErrorAccesoException {
        Usuario result = null;
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();


        // Verifica si la SessionFactory está inicializada.
        if (entityManager == null) {
            throw new ErrorAccesoException("Error en la inicialización de la SessionFactory");
        }

        // Abre una sesión de Hibernate.
        try {
            TypedQuery<Usuario> query = entityManager.createQuery("select u from Usuario u where u.email= :e and u.pass= :p", Usuario.class);
            query.setParameter("e", email);
            query.setParameter("p", pass);
            result = query.getSingleResult();
        } finally {
            entityManager.close();
        }
        // Devuelve el resultado (puede ser null si no se encuentra un usuario).
        return result;
    }
}

