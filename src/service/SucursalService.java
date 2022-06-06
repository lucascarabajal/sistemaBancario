package service;


import dao.IBancoDao;
import dao.ISucursalDao;
import dao.SucursalDao;
import models.Banco;
import models.Sucursal;

import java.util.List;
import java.util.Optional;

public class SucursalService implements ISucursalService{

    private ISucursalDao sucursalDao;

    public SucursalService(){
        this.sucursalDao= new SucursalDao();
    }

    @Override
    public void addSucursal(Sucursal sucursal, Integer idBanco) {
        sucursalDao.addSucursal(sucursal,idBanco);
    }


    @Override
    public Sucursal findSucursalById(Integer idSucursal) {
        return Optional.ofNullable(sucursalDao.findSucursalById(idSucursal)).
                orElseThrow(()->new RuntimeException("No existe sucursal con ese ID."));
    }

    @Override
    public List<Sucursal> listSucursales() {
        return sucursalDao.listSucursales();
    }

    @Override
    public void deleteSucursal(Sucursal deleteSucursal, Sucursal newSucursal) {
        if (sucursalDao.listSucursales().size()<2){
            throw new RuntimeException("No se puede eliminar la sucursal necesita tener más de 1 sucursal.");
        }
        sucursalDao.deleteSucursal(deleteSucursal,newSucursal);
    }

}
