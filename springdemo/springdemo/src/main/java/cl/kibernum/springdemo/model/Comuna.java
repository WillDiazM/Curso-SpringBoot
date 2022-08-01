package cl.kibernum.springdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comunas")
public class Comuna {

    private long idComuna;
    private String descripcion;
    private long idRegion;


    public Comuna(long idComuna, String descripcion, long idRegion) {
        this.idComuna = idComuna;
        this.descripcion = descripcion;
        this.idRegion = idRegion;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(long idComuna) {
        this.idComuna = idComuna;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "idRegion", nullable = false)
    public long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(long idRegion) {
        this.idRegion = idRegion;
    }


}
