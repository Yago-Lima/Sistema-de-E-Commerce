package com.sistema.produtos.repository;


import com.sistema.produtos.model.ItemVenda;
import com.sistema.produtos.model.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendaRepository implements IVendaDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Venda venda) {
        em.persist(venda);
    }

    @Override
    public void update(Venda venda) {
        em.merge(venda);
    }

    @Override
    public void delete(Venda venda) {
        em.remove(venda);
    }

    @Override
    public Venda findById(Long id) {
        return em.find(Venda.class,id);
    }

    @Override
    public List<Venda> findAll() {
        return em.createQuery("from Venda ").getResultList();
    }


    public List<ItemVenda> findAllItemVenda(Long vendaId) {
        Query query = em.createQuery("SELECT item FROM ItemVenda item WHERE item.venda.id = :vendaId");
        query.setParameter("vendaId", vendaId);
        return query.getResultList();
    }


}
