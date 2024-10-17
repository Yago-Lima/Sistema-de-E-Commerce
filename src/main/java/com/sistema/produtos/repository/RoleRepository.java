package com.sistema.produtos.repository;

import com.sistema.produtos.model.Role;
import com.sistema.produtos.model.Usuario;
import jakarta.persistence.TypedQuery;
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

    public Role findByName(String role) {
        String jpql = "SELECT r FROM Role r WHERE r.nome = :nome";
        TypedQuery<Role> query = getEm().createQuery(jpql, Role.class);
        query.setParameter("nome", role.toUpperCase());
        return query.getSingleResult();
    }
}
