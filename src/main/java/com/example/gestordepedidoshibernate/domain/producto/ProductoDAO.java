package com.example.gestordepedidoshibernate.domain.producto;


import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.objectdbutils.ObjectDBUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del patrón de acceso a datos (DAO) para la entidad Producto.
 * Proporciona métodos para realizar operaciones CRUD (Create, Read, Update, Delete) en la base de datos.
 */
public class ProductoDAO implements DAO<Producto> {

    /**
     * Obtiene todos los productos almacenados en la base de datos.
     *
     * @return Lista de productos.
     */
    @Override
    public ArrayList<Producto> getAll() {
        var salida = new ArrayList<Producto>(0);
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        // Abro una sesión de Hibernate.
        try {
            TypedQuery<Producto> query = entityManager.createQuery("select p from Producto p", Producto.class);

            // Obtengo la lista de resultados.
            salida = (ArrayList<Producto>) query.getResultList();
        } finally {
            entityManager.close();
        }
        // Devuelve la lista de Usuarios obtenida de la base de datos.
        return salida;
    }


    /**
     * Obtiene un producto específico por su identificador único.
     *
     * @param id Identificador único del producto.
     * @return Producto encontrado o null si no existe.
     */
    @Override
    public Producto get(Integer id) {
        // En este método, se puede implementar la lógica para obtener un producto por su ID.
        //Aunque en esta ocasión no hace nada.
        return null;
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param data Producto a guardar.
     * @return Producto guardado.
     */
    @Override
    public Producto save(Producto data) {
        // En este método, se puede implementar la lógica para guardar un nuevo producto en la base de datos.
        //Aunque en esta ocasión no hace nada.
        return null;
    }

    /**
     * Actualiza la información de un producto existente en la base de datos.
     *
     * @param data Producto con la información actualizada.
     */
    @Override
    public Producto update(Producto data) {
        return null;
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param data Producto a eliminar.
     */
    @Override
    public void delete(Producto data) {}

    /**
     * Guarda una lista de objetos de tipo Producto en la base de datos.
     *
     * @param data La lista de objetos de tipo Producto que se va a guardar.
     */
    @Override
    public void saveAll(List<Producto> data) {
        EntityManager entityManager = ObjectDBUtils.getEntityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();
            for (Producto pr : data) {
                entityManager.persist(pr);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
