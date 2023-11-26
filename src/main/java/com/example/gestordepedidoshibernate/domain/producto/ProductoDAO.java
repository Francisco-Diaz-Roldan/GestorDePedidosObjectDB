package com.example.gestordepedidoshibernate.domain.producto;


import com.example.gestordepedidoshibernate.domain.dao.DAO;
import com.example.gestordepedidoshibernate.domain.hibernateutils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

        try (Session s = HibernateUtils.getSessionFactory().openSession()) {
            Query<Producto> q = s.createQuery("from Producto", Producto.class);
            salida = (ArrayList<Producto>) q.getResultList();
        }

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
        // En este método, puedes implementar la lógica para obtener un producto por su ID.
        // Devuelve el producto o null si no se encuentra.
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
        // En este método, puedes implementar la lógica para guardar un nuevo producto en la base de datos.
        // Devuelve el producto guardado.
        return null;
    }

    /**
     * Actualiza la información de un producto existente en la base de datos.
     *
     * @param data Producto con la información actualizada.
     */
    @Override
    public void update(Producto data) {
        // En este método, puedes implementar la lógica para actualizar la información de un producto en la base de datos.
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param data Producto a eliminar.
     */
    @Override
    public void delete(Producto data) {
        // En este método, puedes implementar la lógica para eliminar un producto de la base de datos.
    }
}
