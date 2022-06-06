package service;


import models.Banco;
import models.Sucursal;

import java.util.List;

public interface ISucursalService {

    /**
     * Función para añadir una nueva sucursal.
     * @param sucursal Sucursal
     * @param idBanco Integer
     */
    void addSucursal(Sucursal sucursal,Integer idBanco);

    /**
     * Función para buscar sucursales por ID.
     * @param idSucursal Integer
     * @return Sucursal
     */
    Sucursal findSucursalById(Integer idSucursal);

    /**
     * Función para listar sucursales.
     * @return Sucursales
     */
    List<Sucursal> listSucursales();

    /**
     * Función para eliminar una sucursal.
     * @param deleteSucursal Sucursal
     * @param newSucursal Sucursal
     */
    void deleteSucursal(Sucursal deleteSucursal, Sucursal newSucursal);
}
