package cl.kibernum.springdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sucursales")
public class Sucursal {

    private long idSucursal;
    private String descripcion;
    private long idComuna;


    public Sucursal(long idSucursal, String descripcion, long idComuna) {
        this.idSucursal = idSucursal;
        this.descripcion = descripcion;
        this.idComuna = idComuna;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "idProducto", nullable = false)
    public long getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(long idComuna) {
        this.idComuna = idComuna;
    }
}
