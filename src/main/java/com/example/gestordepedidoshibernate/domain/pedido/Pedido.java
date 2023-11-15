package com.example.gestordepedidoshibernate.domain.pedido;

import com.example.gestordepedidoshibernate.domain.item.Item;

import java.util.ArrayList;
/**
 * Clase que representa un pedido realizado por un usuario.
 */
public class Pedido {
    private Integer id_pedido;           // Identificador único del pedido.
    private String codigo_pedido;        // Código único asociado al pedido.
    private String fecha;                // Fecha de emisión del pedido.
    private Integer usuario;             // Identificador del usuario que realizó el pedido.
    private Integer total;               // Total del pedido.
    private ArrayList<Item> item;        // Lista de ítems incluidos en el pedido.

    /**
     * Constructor para crear un objeto Pedido con todos los atributos.
     *
     * @param id_pedido     Se refiere al ID del pedido.
     * @param codigo_pedido Se refiere al código único asociado al pedido.
     * @param fecha         Se refiere a la fecha de emisión del pedido.
     * @param usuario       Se refiere al ID del usuario que realizó el pedido.
     * @param total         Se refiere al total del pedido.
     * @param item          Se refiere a la lista de ítems incluidos en el pedido.
     */
    public Pedido(Integer id_pedido, String codigo_pedido, String fecha, Integer usuario, Integer total, ArrayList<Item> item) {
        this.id_pedido = id_pedido;
        this.codigo_pedido = codigo_pedido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.total = total;
        this.item = item;
    }

    /**
     * Constructor por defecto que crea un objeto Pedido sin inicializar sus atributos.
     */
    public Pedido() {
    }

    /**
     * Obtiene el ID del pedido.
     *
     * @return El ID del pedido.
     */
    public Integer getId_pedido() {
        return id_pedido;
    }

    /**
     * Establece el ID del pedido.
     *
     * @param id_pedido El ID del pedido.
     */
    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    /**
     * Obtiene el código único asociado al pedido.
     *
     * @return El código único del pedido.
     */
    public String getCodigo_pedido() {
        return codigo_pedido;
    }

    /**
     * Establece el código único asociado al pedido.
     *
     * @param codigo_pedido El código único del pedido.
     */
    public void setCodigo_pedido(String codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    /**
     * Obtiene la fecha de emisión del pedido.
     *
     * @return La fecha de emisión del pedido.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de emisión del pedido.
     *
     * @param fecha La fecha de emisión del pedido.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el ID del usuario que realizó el pedido.
     *
     * @return El ID del usuario que realizó el pedido.
     */
    public Integer getUsuario() {
        return usuario;
    }

    /**
     * Establece el ID del usuario que realizó el pedido.
     *
     * @param usuario El ID del usuario que realizó el pedido.
     */
    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el total del pedido.
     *
     * @return El total del pedido.
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Establece el total del pedido.
     *
     * @param total El total del pedido.
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * Obtiene la lista de ítems incluidos en el pedido.
     *
     * @return La lista de ítems del pedido.
     */
    public ArrayList<Item> getItem() {
        return item;
    }

    /**
     * Establece la lista de ítems incluidos en el pedido.
     *
     * @param item La lista de ítems del pedido.
     */
    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    /**
     * Genera una representación en forma de cadena del objeto Pedido.
     *
     * @return Una cadena que representa el objeto Pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "id del pedido: " + id_pedido +
                ", código del pedido: '" + codigo_pedido + '\'' +
                ", fecha de emisión del pedido: '" + fecha + '\'' +
                ", ID del usuario: " + usuario +
                ", total: " + total +
                ", ítems: " + item +
                '}';
    }
}
