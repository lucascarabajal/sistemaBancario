package service;

import dao.CuentaDao;
import dao.ICuentaDao;
import models.Cuenta;
import models.Persona;

import java.util.List;
import java.util.Optional;

public class CuentaService implements ICuentaService {

    private ICuentaDao cuentaDao;

    private static long MAX = 999999999999999999L;
    private static long MIN = 100000000000000000L;

    public CuentaService() {
        this.cuentaDao = new CuentaDao();
    }

    @Override
    public void addCuenta(Cuenta cuenta) {
        long range =(MAX-MIN)+1;
        cuenta.setCbu((long)Math.floor(Math.random()*range)+MIN);
        cuenta.setSaldo(0.0d);
        cuentaDao.addCuenta(cuenta);
    }

    @Override
    public Double consultarSaldo(Cuenta cuenta) {
        return cuenta.getSaldo();
    }

    @Override
    public List<Cuenta> listarCuentaByDni(String dni) {
        return Optional.ofNullable(cuentaDao.findCuentasByDni(dni)).
                orElseThrow(()->new RuntimeException("No existe cuentas con ese DNI."));
    }

    @Override
    public Cuenta findCuentaById(Integer id) {
        return Optional.ofNullable(cuentaDao.findCuentaById(id)).
                orElseThrow(()->new RuntimeException("No existe cuentas con ese ID."));
    }

    @Override
    public void depositar(Cuenta cuenta, Double monto){
        cuenta.setSaldo(cuenta.getSaldo()+monto);
        cuentaDao.updateSaldo(cuenta);
    }

    @Override
    public void extraer(Cuenta cuenta, Double monto) {
        if (cuenta.getSaldo()<monto){
            throw new IllegalStateException("No se puede realizar la extracción por fondos insuficientes");
        }
        cuenta.setSaldo(cuenta.getSaldo()-monto);
        cuentaDao.updateSaldo(cuenta);
    }

    @Override
    public void transferir(Cuenta cuenta, Long cbuDestino, Double monto) {
        extraer(cuenta,monto);
        depositar(cuentaDao.findCuentaByCbu(cbuDestino),monto);
    }
}
