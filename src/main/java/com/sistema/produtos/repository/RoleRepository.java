package com.sistema.produtos.repository;

import com.sistema.produtos.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository extends AbstractRepository implements IRoleDao {

    @Override
    public void save(Role role) {
        getEm().persist(role);
    }

    @Override
    public void update(Role role) {
        getEm().merge(role);
    }

    @Override
    public void delete(Role role) {
        getEm().remove(role);
    }

    @Override
    public Role findById(Long id) {
        return getEm().find(Role.class,id);
    }

    @Override
    public List<Role> findAll() {
        return getEm().createQuery("SELECT r FROM Role r").getResultList();
    }
}
