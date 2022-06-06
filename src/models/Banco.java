package models;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    //Atributos
    private Integer id;
    private String nombre;
    private List<Cuenta> cuentas;
    private List<Sucursal> sucursales;

    //Constructor

    public Banco(){}

    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
        this.sucursales = new ArrayList<>();
    }

    //Getter and Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
