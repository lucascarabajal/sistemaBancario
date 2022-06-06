package models;

import java.util.Random;
import java.util.UUID;

public class Cuenta {

    //Atributos
    private Integer id;
    private Long cbu;
    private Double saldo;
    private Integer tipoMoneda;
    private Integer tipoCuenta;
    private Banco banco;
    private Cliente cliente;

    @Override
    public String toString() {
        return "Cuenta{" +
                 id +
                "- cbu=" + cbu +
                ", saldo=" + saldo +
                ", tipoMoneda=" + tipoMoneda +
                ", tipoCuenta=" + tipoCuenta +
                ", cliente=" + cliente +
                '}';
    }

    //Constructor
    public Cuenta(Integer tipoMoneda, Integer tipoCuenta ,Banco banco ,Cliente cliente) {
        this.tipoMoneda = tipoMoneda;
        this.tipoCuenta = tipoCuenta;
        this.banco = banco;
        this.cliente = cliente;
        this.saldo=0.0;
    }

    public Cuenta(){}

    //Getter and Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(Integer tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(Integer tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
