package service;

import dao.BancoDao;
import dao.IBancoDao;
import models.Banco;
import models.Cuenta;

public class BancoService implements IBancoService{

    private IBancoDao bancoDao;

    public BancoService(){
        this.bancoDao  = new BancoDao();
    }

    @Override
    public void addBanco(Banco banco) {
        bancoDao.addBanco(banco);
    }

    @Override
    public Banco findBancoById(Integer idBanco) {
        return bancoDao.findBancoById(idBanco);
    }

}
