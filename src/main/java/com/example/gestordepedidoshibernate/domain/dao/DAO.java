package com.example.gestordepedidoshibernate.domain.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos.
 *
 * @param <T> Tipo de entidad que se manejará en la base de datos.
 */
public interface DAO<T> {
    /**
     * Obtiene todos los registros del tipo especificado.
     *
     * @return Lista de todos los registros.
     */
    public ArrayList<T> getAll();

    /**
     * Obtiene un registro del tipo especificado según su identificador único.
     *
     * @param id Identificador único del registro.
     * @return El registro correspondiente al identificador proporcionado, o null si no se encuentra.
     */
    public T get(Integer id);

    /**
     * Guarda un nuevo registro en la base de datos o actualiza un registro existente.
     *
     * @param data El objeto a ser guardado o actualizado.
     * @return El objeto guardado o actualizado.
     */
    public T save(T data);

    /**
     * Actualiza un registro existente en la base de datos.
     *
     * @param data El objeto a ser actualizado.
     */
    public T update(T data);

    /**
     * Elimina un registro existente de la base de datos.
     *
     * @param data El objeto a ser eliminado.
     */
    public void delete(T data);

    /**
     * Guarda todos los registros existentes de la base de datos.
     *
     * @param data El objeto a ser guardado.
     */
    public void saveAll(List<T> data);
}
