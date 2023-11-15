package com.example.gestordepedidoshibernate.domain.producto;
/**
 * Clase que representa un producto en el sistema de gestión de pedidos.
 */
public class Producto {
    private Integer id_producto;
    private String nombre;
    private Double precio;
    private Integer cantidad_disponible;

    /**
     * Constructor que crea un nuevo objeto Producto con los atributos especificados.
     *
     * @param id_producto         Hace referencia al ID del producto.
     * @param nombre              Se refiere al nombre del producto.
     * @param precio              Se refiere al precio del producto.
     * @param cantidad_disponible Hace referencia a la cantidad disponible del producto en el inventario.
     */
    public Producto(Integer id_producto, String nombre, Double precio, Integer cantidad_disponible) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
    }

    /**
     * Constructor sin argumentos que crea un objeto Producto vacío.
     */
    public Producto() {
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(Integer cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    /**
     * Sobrescribe el método toString para representar el producto como una cadena de texto.
     *
     * @return Una representación de cadena del producto en el formato "nombre precio€".
     */
    @Override
    public String toString() {
        return nombre + " " + precio + "€";
    }
}
