package cl.kibernum.springdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    private long idProducto;
    private long idCategoria;
    private String descripcion;
    private int precio;
    private int stock;


    public Producto(long idProducto, long idCategoria, String descripcion, int precio, int stock) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    @Column(name = "idCategoria", nullable = false)
    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "precio", nullable = false)
    public int getPrecio() { return precio; }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Column(name = "stock", nullable = false)
    public int getStock() { return stock; }

    public void setStock(int fecha) {
        this.stock = stock;
    }

}
