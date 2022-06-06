package dao;

import models.Cuenta;

import java.util.List;

public interface ICuentaDao {
    void addCuenta(Cuenta cuenta);
    List<Cuenta> findCuentasByDni(String dni);
    Cuenta findCuentaById(Integer idCuenta);
    Cuenta findCuentaByCbu(Long cbu);
    void updateSaldo(Cuenta cuenta);
}
