package com.step.springmvcapp.dao;

import com.step.springmvcapp.entity.Admin;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository 
public class AdminDAOImpl implements AdminDAO {

    @PersistenceContext
    private EntityManager em;
  
    @Override
    public Admin getAdmin(Long id) {
        String sql = "from Admin a WHERE a.id=:id"; 
       // Query query = session.createQuery("from Admin c WHERE c.id="+1L);
        return (Admin)this.em.createQuery(sql).setParameter("id",id).getSingleResult();
    }

    @Override
    public void save(Admin obj) {
        if (obj.getId() == null) {
            this.em.persist(obj);
        } else {
            this.em.merge(obj);
        }
    }
    
    @Override
    public void delete(Long id) {
        String sql = "from Admin a WHERE a.id=:id"; //
        Admin a = (Admin)this.em.createQuery(sql).setParameter("id",id).getSingleResult();        
        this.em.remove(a);
    }    
}
