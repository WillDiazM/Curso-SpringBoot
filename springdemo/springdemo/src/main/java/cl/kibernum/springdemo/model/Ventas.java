package cl.kibernum.springdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ventas")
public class Ventas {
    private long idVenta;
    private long idEmpleado;
    private long idCliente;
    private String fecha;


    public Ventas(long idVenta, long idEmpleado, long idCliente, String fecha) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.fecha = fecha;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    @Column(name = "idEmpleado", nullable = false)
    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Column(name = "idCliente", nullable = false)
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    @Column(name = "fecha", nullable = false)
    public String getFecha() { return fecha; }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
