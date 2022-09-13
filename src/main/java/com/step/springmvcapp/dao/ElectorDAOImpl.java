package com.step.springmvcapp.dao;

import com.step.springmvcapp.entity.Elector;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository // является производными от @Component
public class ElectorDAOImpl implements ElectorDAO {
    @PersistenceContext
    private EntityManager em;
    
    public ElectorDAOImpl() {}
    
    @Override
    public Elector findById(Long id) {
        String sql = "from Elector c WHERE c.id=:id"; 
        return (Elector)this.em.createQuery(sql).setParameter("id",id).getSingleResult();
    }
    
    @Override
    public List<Elector> findAll() {
        javax.persistence.Query query = this.em.createQuery("from Elector");
        return query.getResultList();
    }
    
    @Override
    public void save(Elector obj) {
        if (obj.getId() == null) {
            this.em.persist(obj);
        } else {
            this.em.merge(obj);
        }
    }
    
    @Override
    public void saveAll(Collection<Elector> obj) {
        for (Elector e: obj){
            if (e.getId() == null) {
                this.em.persist(obj);
            } else {
                this.em.merge(obj);// Чтобы избежать NonUniqueObjectException или update
            } 
        }
    }
    
    @Override
    public void delete(Long id) {
        String sql = "from Elector e WHERE e.id=:id"; //
        Elector e = (Elector)this.em.createQuery(sql).setParameter("id",id).getSingleResult();        
        this.em.remove(e);
    }
}
