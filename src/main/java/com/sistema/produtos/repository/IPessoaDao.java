package com.sistema.produtos.repository;

import com.sistema.produtos.model.Pessoa;

import java.util.List;

public interface IPessoaDao {
    void save(Pessoa pessoa);
    void update(Pessoa pessoa);
    void delete(Pessoa pessoa);
    Pessoa findById(Long id);
    List<Pessoa> findAll();
}
