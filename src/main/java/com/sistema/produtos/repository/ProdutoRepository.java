package com.sistema.produtos.repository;

import com.sistema.produtos.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
    public List<Produto> findByName(String nome) {
        Query query = em.createQuery("SELECT p FROM Produto p WHERE lower(p.nome)  LIKE lower(:name)");
        query.setParameter("name", "%" + nome.toLowerCase() + "%");
        return query.getResultList();
    }

    public List<Produto> findByPrice(Double precoMin, Double precoMax) {
        Query query = em.createQuery("SELECT p FROM Produto p WHERE p.valor BETWEEN :precoMin AND :precoMax");
        query.setParameter("precoMin", precoMin);
        query.setParameter("precoMax", precoMax);
        return query.getResultList();
    }
}
