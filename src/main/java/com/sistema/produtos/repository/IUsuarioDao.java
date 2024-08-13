package com.sistema.produtos.repository;

import com.sistema.produtos.model.Usuario;

import java.util.List;

public interface IUsuarioDao {
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(Usuario usuario);
    Usuario findById(Long id);
    List<Usuario> findAll();
}
