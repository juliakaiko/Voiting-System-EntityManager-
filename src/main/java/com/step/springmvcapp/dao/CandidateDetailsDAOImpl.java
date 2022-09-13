package com.step.springmvcapp.dao;

import com.step.springmvcapp.entity.CandidateDetails;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository // является производными от @Component
public class CandidateDetailsDAOImpl implements  CandidateDetailsDAO{
    @PersistenceContext
    private EntityManager em;
  
    @Override
    public CandidateDetails findById(Long id) {
        //String sql = "from CandidateDetails c JOIN FETCH c.candidate WHERE c.id=:id"; //JOIN FETCH c.details 
        String sql="from CandidateDetails c WHERE c.id=:id";
        return (CandidateDetails)this.em.createQuery(sql).setParameter("id",id).getSingleResult();
    }
    
    @Override
    public List<CandidateDetails> findAll() { 
        javax.persistence.Query query = this.em.createQuery("SELECT cd FROM CandidateDetails cd JOIN FETCH cd.candidate");//from CandidateDetails
        return query.getResultList();
    }
    
    @Override
    public void save(CandidateDetails obj) {
        if (obj.getId() == null) {
            this.em.persist(obj);
        } else {
            this.em.merge(obj);
        }
    }
    
    @Override
    public void saveAll(Collection<CandidateDetails> obj) {
        for (CandidateDetails c: obj){
            if (c.getId() == null) {
                this.em.persist(obj);
            } else {
                this.em.merge(obj);// Чтобы избежать NonUniqueObjectException или update
            } 
        }
    }
    
    @Override
    public void delete(Long id) {
        String sql = "from CandidateDetails c WHERE c.id=:id"; //
        CandidateDetails c = (CandidateDetails)this.em.createQuery(sql).setParameter("id",id).getSingleResult();        
        this.em.remove(c);
    }   
}
