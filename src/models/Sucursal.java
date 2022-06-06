package models;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {

    //Atributos
    private Integer id;
    private String direccion;
    private Cliente cliente;

    //Constructor

    public Sucursal(){}

    public Sucursal(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    //Getter and Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
