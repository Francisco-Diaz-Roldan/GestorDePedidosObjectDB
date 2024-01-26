package com.example.gestordepedidoshibernate.domain.pedido;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.objectdbutils.ObjectDBUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();

        // Abro una sesión de Hibernate.
        try {
            TypedQuery<Pedido> query = entityManager.createQuery("select p from Pedido p", Pedido.class);
            // Obtengo la lista de resultados.
            salida = (ArrayList<Pedido>) query.getResultList();
        } finally {
            entityManager.close();
        }

        // Devuelve la lista de Usuarios obtenida de la base de datos.
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
        Pedido salida = null;
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        // Crea una sesión de Hibernate.
        try {
            TypedQuery<Pedido> query = entityManager.createQuery("select p from Pedido p where  p.id_pedido= :id", Pedido.class);
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
     * Guarda un nuevo pedido o actualiza uno existente en la base de datos.
     *
     * @param data Pedido a ser guardado o actualizado.
     * @return Pedido guardado o actualizado.
     */
    @Override
    public Pedido save(Pedido data) {
        // Abre una sesión de EntityManager
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();

        try {
            // Inicia una transacción.
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            entityManager.getTransaction().commit();
            }finally{
            entityManager.close();
        }
        // Devuelve el objeto Pedido.
        return data;
    }

    /**
     * Actualiza un pedido existente en la base de datos.
     *
     * @param data Pedido a ser actualizado.
     */
    public Pedido update(Pedido data) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            data = entityManager.merge(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return data;
    }

    /**
     * Elimina un pedido de la base de datos.
     *
     * @param data Pedido a ser eliminado.
     */
    public void delete(Pedido data) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            // Si la entidad no está gestionada, primero la adjuntamos al contexto de persistencia.
            if (!entityManager.contains(data)) {
                data = entityManager.merge(data);
            }
            entityManager.remove(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
    }

    /**
     * Guarda una lista de objetos de tipo Pedido en la base de datos.
     *
     * @param data La lista de objetos de tipo Pedido que se va a guardar.
     */
    @Override
    public void saveAll(List<Pedido> data) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();
            for (Pedido p : data) {
                entityManager.persist(p);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}