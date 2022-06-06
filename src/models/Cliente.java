package models;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Cliente extends Persona{

    //Atributos
    private Date fechaAlta;
    private Integer id;
    private Sucursal sucursal;
    private List<Cuenta> cuentas;

    //Constructor
    public Cliente(){
        super();
    }

    public Cliente(String nombre, String apellido, String dni, Date fechaNac, String email, String numTel,Date fechaAlta, Sucursal sucursal) {
        super(nombre,apellido,dni,fechaNac,email,numTel);
        this.fechaAlta = fechaAlta;
        this.sucursal = sucursal;
        this.cuentas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id= "+ getId() +
                ", sucursal=" + sucursal +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }

    //Getter and Setter
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
