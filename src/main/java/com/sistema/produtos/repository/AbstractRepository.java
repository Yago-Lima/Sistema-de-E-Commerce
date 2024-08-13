package com.sistema.produtos.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractRepository {
    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }
}
