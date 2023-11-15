package com.example.gestordepedidoshibernate.domain.dao;

import com.example.gestordepedidoshibernate.domain.excepciones.PasswordIncorrectaException;
import com.example.gestordepedidoshibernate.domain.excepciones.UsuarioIncorrectoException;

import java.sql.Array;
import java.util.ArrayList;

public interface DAO<T> {
    public ArrayList<T> getAll();
    public T get(Integer id);
    public T save(T data);
    public void update(T data);
    public void delete(T data);
}
