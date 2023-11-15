package com.example.gestordepedidoshibernate.domain.producto;

/**
 * Interfaz que define métodos para acceder a datos de productos en el sistema de gestión de pedidos.
 */
public interface ProductoDAO {

    /**
     * Recupera un producto por su ID desde el origen de datos.
     *
     * @param id_producto El ID del producto que se desea cargar.
     * @return El objeto Producto correspondiente al ID especificado.
     */
    public Producto loadProducto(Integer id_producto);
}
