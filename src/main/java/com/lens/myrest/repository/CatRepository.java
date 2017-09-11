package com.lens.myrest.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.lens.myrest.model.Cat;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.List;

@Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
public class CatRepository {
	@PersistenceContext(unitName = "CatsPU")
    private EntityManager em;

    public Cat find(@NotNull Long id){
        return em.find(Cat.class,id);
    }
    
    @Transactional(REQUIRED)
    public Cat create(@NotNull Cat cat){
    	System.out.println("persisting cat: " + cat.toString());
        //em.persist(cat);
    	em.merge(cat);
    	em.flush();
        return cat;
    }
    
    @Transactional(REQUIRED)
    public void delete(@NotNull Long id){
        em.remove(em.getReference(Cat.class,id));
    }

    public List<Cat> findAll(){
        TypedQuery<Cat> query = em.createQuery("SELECT c from Cat c order by c.id",Cat.class);
        return query.getResultList();
    }

    public Long countAll(){
        TypedQuery<Long> query = em.createQuery("SELECT count(c) from Cat c",Long.class);
        return query.getSingleResult();
    }
}
