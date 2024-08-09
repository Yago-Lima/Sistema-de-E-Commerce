package com.sistema.produtos.repository;

import com.sistema.produtos.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepository implements  IPessoaDao{
    @PersistenceContext
    private EntityManager em;
    @Override
    public void save(Pessoa pessoa) {
        em.persist(pessoa);
    }

    @Override
    public void update(Pessoa pessoa) {
        em.merge(pessoa);
    }

    @Override
    public void delete(Pessoa p) {
        em.remove(p);
    }

    @Override
    public Pessoa findById(Long id) {
        return em.find(Pessoa.class,id);
    }

    @Override
    public List<Pessoa> findAll() {
        return em.createQuery("SELECT p FROM Pessoa p").getResultList();
    }
}
