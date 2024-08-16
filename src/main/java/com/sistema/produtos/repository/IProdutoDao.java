package com.sistema.produtos.repository;

import com.sistema.produtos.model.Produto;

import java.util.List;

public interface IProdutoDao {
    void save(Produto produto);
    void update(Produto produto);
    void delete(Produto produto);
    Produto findById(Long id);
    List<Produto> findAll();
    List<Produto> findByName(String nome);
    List<Produto> findByPrice(Double precoMin, Double precoMax);
}
