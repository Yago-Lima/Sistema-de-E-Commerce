package com.sistema.produtos.repository;

import com.sistema.produtos.model.Pessoa;
import com.sistema.produtos.model.Venda;

import java.util.List;

public interface IVendaDao {
    void save(Venda venda);
    void update(Venda venda);
    void delete(Venda venda);
    Venda findById(Long id);
    List<Venda> findAll();
}
