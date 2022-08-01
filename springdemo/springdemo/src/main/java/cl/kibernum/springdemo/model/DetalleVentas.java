package cl.kibernum.springdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVentas {
    private long idVenta;
    private int cantidad;
    private long idProducto;


    public DetalleVentas(long idVenta, int cantidad, long idProducto) {
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.idProducto = idProducto;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    @Column(name = "cantidad", nullable = false)
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "idProducto", nullable = false)
    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }
}
