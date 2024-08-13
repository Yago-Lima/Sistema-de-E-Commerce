package com.sistema.produtos.repository;

import com.sistema.produtos.model.Pessoa;
import com.sistema.produtos.model.Role;

import java.util.List;

public interface IRoleDao {
    void save(Role role);
    void update(Role role);
    void delete(Role role);
    Role findById(Long id);
    List<Role> findAll();
}
