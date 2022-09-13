package com.step.springmvcapp.dao;

import com.step.springmvcapp.entity.Candidate;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository // является производными от @Component
public class CandidateDAOImpl implements CandidateDAO{  
    @PersistenceContext
    private EntityManager em;

    @Override
    public Candidate findById(Long id) {//!!! (fetch = FetchType.LAZY) для JOIN FETCH 
        String sql = "from Candidate c WHERE c.id=:id"; //c JOIN FETCH c.details 
        //"from Candidate c JOIN FETCH  c.details WHERE c.id=:id"
        return (Candidate)this.em.createQuery(sql).setParameter("id",id).getSingleResult();
    }

    @Override
    public List<Candidate> findAll() {
        Query query = this.em.createQuery("from Candidate");
        return query.getResultList();
    }

    @Override
    public void save(Candidate obj) {
        if (obj.getId() == null) {
            this.em.persist(obj);
        } else {
            this.em.merge(obj);
        }
    }
       
     @Override
    public void saveAll(Collection<Candidate> obj) {
        for (Candidate c: obj){
            if (c.getId() == null) {
                this.em.persist(obj);
            } else {
                this.em.merge(obj);// Чтобы избежать NonUniqueObjectException или update
            } 
        }
    }
    
    @Override
    public void delete(Long id) {
        String sql = "from Candidate c WHERE c.id=:id"; //JOIN FETCH c.details 
        Candidate c = (Candidate)this.em.createQuery(sql).setParameter("id",id).getSingleResult();        
        this.em.remove(c);
    }   
}
