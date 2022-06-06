package service;

import models.Banco;


public interface IBancoService {
    void addBanco(Banco banco);
    Banco findBancoById(Integer idBanco);
}
