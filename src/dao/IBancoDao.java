package dao;

import models.Banco;

public interface IBancoDao {
    void addBanco(Banco banco);
    Banco findBancoById(Integer idBanco);
}
