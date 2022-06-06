package service;

import models.Cliente;
import models.Sucursal;

import java.util.List;

public interface IClienteService {

    /**
     * Función para agregar un nuevo cliente.
     * @param cliente Cliente
     */
    void addCliente(Cliente cliente);

    /**
     * Función para buscar un cliente por su dni.
     * @param dni String
     * @return Cliente
     */
    Cliente findByClienteByDni(String dni);

    /**
     * Función para listar clientes.
     * @return clientes
     */
    List<Cliente> listClientes();

    /**
     * Función para listar cliente por sucursales.
     * @param sucursal Sucursal
     * @return clientes
     */
    List<Cliente> listClientesBySucursal(Sucursal sucursal);
}
