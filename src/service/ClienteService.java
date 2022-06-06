package service;

import dao.ClienteDao;
import dao.CuentaDao;
import dao.IClienteDao;
import models.Cliente;
import models.Sucursal;

import java.util.List;
import java.util.Optional;

public class ClienteService implements IClienteService{

    private IClienteDao clienteDao;

    public ClienteService(){
        this.clienteDao= new ClienteDao();
    }

    @Override
    public void addCliente(Cliente cliente) {
        clienteDao.addCliente(cliente);
    }

    @Override
    public Cliente findByClienteByDni(String dni) {
        return Optional.ofNullable(clienteDao.findByClienteByDni(dni)).
                orElseThrow(()->new RuntimeException("No existe cliente con este DNI."));
    }

    @Override
    public List<Cliente> listClientes() {
        return clienteDao.listClientes();
    }

    @Override
    public List<Cliente> listClientesBySucursal(Sucursal sucursal) {
        return clienteDao.listClientesBySucursal(sucursal);
    }

}
