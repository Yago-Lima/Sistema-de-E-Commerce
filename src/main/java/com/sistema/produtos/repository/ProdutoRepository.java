package com.sistema.produtos.repository;

import com.sistema.produtos.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository implements IProdutoDao{
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Produto produto) {
        em.persist(produto);
    }

    @Override
    public void update(Produto produto) {
        em.merge(produto);
    }

    @Override
    public void delete(Produto p) {
        em.remove(p);
    }

    @Override
    public Produto findById(Long id) {
        return em.find(Produto.class,id);
    }

    @Override
    public List<Produto> findAll() {
        return em.createQuery("SELECT p FROM Produto p").getResultList();
    }
}
