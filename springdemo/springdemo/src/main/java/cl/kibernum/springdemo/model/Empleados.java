package cl.kibernum.springdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleados {
    private long idEmpleado;
    private long idSucursal;
    private String nombre;
    private String apePat;
    private String apeMat;


    public Empleados(long idEmpleado, long idSucursal, String nombre, String apePat, String apeMat) {
        this.idEmpleado = idEmpleado;
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.apePat = apePat;
        this.apeMat = apeMat;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Column(name = "idSucursal", nullable = false)
    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apePat", nullable = false)
    public String getApePat() { return apePat; }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    @Column(name = "apeMat", nullable = false)
    public String getApeMat() { return apeMat; }

    public void setApeMat(String fecha) {
        this.apeMat = apeMat;
    }
}
