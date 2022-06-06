package service;

import models.Cuenta;
import models.Persona;

import java.util.List;

public interface ICuentaService {

    /**
     * Función para agregar cuentas.
     * @param cuenta Cuenta
     */
    void addCuenta(Cuenta cuenta);

    /**
     * Función para consultar el saldo de una cuenta.
     * @param cuenta Cuenta
     * @return saldo
     */
    Double consultarSaldo(Cuenta cuenta);

    /**
     * Función para listar las cuentas de un cliente.
     * @param dni String
     * @return Cuentas
     */
    List<Cuenta> listarCuentaByDni(String dni);

    /**
     * Función para buscar una cuenta por ID.
     * @param id Integer
     * @return Cuenta
     */
    Cuenta findCuentaById(Integer id);

    /**
     * Función para depositar dinero a una cuenta.
     * @param cuenta Cuenta
     * @param monto Double
     */
    void depositar(Cuenta cuenta, Double monto);

    /**
     * Función para extraer dinero de una cuenta.
     * @param cuenta Cuenta
     * @param monto Double
     */
    void extraer(Cuenta cuenta, Double monto);

    /**
     * Función para realizar una transferencia entre dos cuentas.
     * @param cuenta Cuenta
     * @param cbu Long
     * @param monto Double
     */
    void transferir(Cuenta cuenta, Long cbu, Double monto);
}
