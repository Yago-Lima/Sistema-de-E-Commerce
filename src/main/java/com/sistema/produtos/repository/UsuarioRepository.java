package com.sistema.produtos.repository;

import com.sistema.produtos.model.Role;
import com.sistema.produtos.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements IUsuarioDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        em.remove(usuario);
    }

    @Override
    public Usuario findById(Long id) {
        return em.find(Usuario.class,id);
    }
    public Usuario findByName(String login) {
        String jpql = "SELECT u FROM Usuario u WHERE u.login = :login";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setParameter("login", login.toLowerCase());
        return query.getSingleResult();
    }

    @Override
    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u").getResultList();
    }
}
