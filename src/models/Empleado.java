package models;

import java.sql.Date;

public class Empleado extends Persona{
    //Atributos
    private String cargo;

    //Constructor
    public Empleado(String nombre, String apellido, String dni, Date fechaNac, String email, String numTel, String cargo) {
        super(nombre, apellido, dni, fechaNac, email, numTel);
        this.cargo=cargo;
    }

    //Getter and Setter
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
