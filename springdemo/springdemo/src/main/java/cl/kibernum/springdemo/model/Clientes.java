package cl.kibernum.springdemo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Clientes {
    private long idCliente;
    private String nombre;
    private String ApePat;
    private String ApeMat;
    private String telefono;
    private String email;
    private long idComuna;

    public Clientes(long idCliente, String nombre, String ApePat, String ApeMat, long idComuna, String telefono, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.ApePat = ApePat;
        this.ApeMat = ApeMat;
        this.telefono = telefono;
        this.email = email;
        this.idComuna = idComuna;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "ApePat", nullable = false)
    public String getApePat() {
        return ApePat;
    }

    public void setApePat(String ApePat) {
        this.ApePat = ApePat;
    }

    @Column(name = "ApeMat", nullable = false)
    public String getApeMat() {
        return ApeMat;
    }

    public void setApeMat(String ApeMat) {
        this.ApeMat = ApeMat;
    }

    @Column(name = "telefono", nullable = false)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "idComuna", nullable = false)
    public long getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(long idComuna) {
        this.idComuna = idComuna;
    }
}
