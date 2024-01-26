package com.example.gestordepedidoshibernate.domain.item;

import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.objectdbutils.ObjectDBUtils;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase que implementa la interfaz DAO para la entidad Item, proporcionando métodos para acceder y manipular datos
 * relacionados con los items en la base de datos.
 */
public class ItemDAO implements DAO<Item> {

    /**
     * Obtiene todos los items de la base de datos.
     *
     * @return Una lista de todos los items en la base de datos.
     */

    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Item> query = entityManager.createQuery("select i from Item i", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        } finally {
            entityManager.close();
        }
        return salida;
    }

    /**
     * Obtiene un item de la base de datos por su identificador único.
     *
     * @param id El identificador único del item.
     * @return El item correspondiente al identificador dado.
     */
    @Override
    public Item get(Integer id) {
        // Crea una instancia de Item para almacenar el resultado.
        Item salida = null;
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<Item> query = entityManager.createQuery("select i from Item i where i.id= :id", Item.class);
            query.setParameter("id", id);
            // Utiliza el método `get` de Hibernate para obtener el objeto Item por su identificador.
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
     * Guarda un nuevo item o actualiza uno existente en la base de datos.
     *
     * @param data El item a guardar o actualizar.
     * @return El item guardado o actualizado.
     */
    @Override
    public Item save(Item data) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return data;// Si da problemas -> return null
    }

    /**
     * Actualiza un item existente en la base de datos.
     *
     * @param data El item a actualizar.
     */
    @Override
    public Item update(Item data) {
        // Abre una sesión de Hibernate.
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
        return data;// Si da problemas -> return null
    }

    /**
     * Elimina un item de la base de datos.
     *
     * @param data El item a eliminar.
     */
    @Override
    public void delete(Item data) {
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
     * Guarda una lista de objetos de tipo Item en la base de datos.
     *
     * @param data La lista de objetos de tipo Item que se va a guardar.
     */
    @Override
    public void saveAll(List<Item> data) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            for (Item i : data) {
                entityManager.persist(i);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}