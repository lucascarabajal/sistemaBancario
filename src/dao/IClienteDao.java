package dao;

import models.Cliente;
import models.Sucursal;

import java.util.List;

public interface IClienteDao {
    void addCliente(Cliente cliente);
    Cliente findByClienteByDni(String dni);
    Cliente findClienteById(Integer idCliente);
    List<Cliente> listClientes();
    List<Cliente> listClientesBySucursal(Sucursal sucursal);
}
