package dao;

import models.Banco;
import models.Sucursal;

import java.util.List;

public interface ISucursalDao {
    void addSucursal(Sucursal sucursal,Integer idBanco);
    Sucursal findSucursalById(Integer id);
    List<Sucursal> listSucursales();
    void deleteSucursal(Sucursal deleteSucural, Sucursal newSucursal);
}

