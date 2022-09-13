package com.step.springmvcapp.service;

import com.step.springmvcapp.dao.AdminDAO;
import com.step.springmvcapp.dao.CandidateDAO;
import com.step.springmvcapp.dao.CandidateDetailsDAO;
import com.step.springmvcapp.dao.ElectorDAO;
import com.step.springmvcapp.entity.Admin;
import com.step.springmvcapp.entity.Candidate;
import com.step.springmvcapp.entity.CandidateDetails;
import com.step.springmvcapp.entity.Elector;
import java.util.Collection;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
//@Service
public class VoitingServiceForAdminImpl implements VoitingServiceForAdmin{
     
    @Autowired
    private CandidateDAO candidateDao;
    @Autowired
    private ElectorDAO electorDao;
    @Autowired
    private CandidateDetailsDAO detailsDao;
    @Autowired
    private AdminDAO adminDao;
    @Autowired
    private VoitingServiceForUser userServ;// = new VoitingServiceForUserImpl ();

    public VoitingServiceForAdminImpl() {}

    public VoitingServiceForUser getUserServ() {
        return userServ;
    }

    public void setUserServ(VoitingServiceForUser userServ) {
        this.userServ = userServ;
    }
    
    @Override
    @Transactional // добавляет транзакцию!
    public void saveCandidate(Candidate candidate) {
        candidateDao.save(candidate);
    }

    @Override
    @Transactional
    public void addAllCandidates(Collection<Candidate> candidates) {
        for (Candidate c: candidates){
            candidateDao.save(c);
        }
    }

    @Override
    @Transactional
    public void deleteCandidateById(Long id) {
       candidateDao.delete(id);
    }

    @Override
    @Transactional
    public void saveElector(Elector elector) {
        electorDao.save(elector);
    }

    @Override
    @Transactional
    public void addAllElectors(Collection<Elector> electors) {
        for (Elector e: electors){
            electorDao.save(e);
        }
    }

    @Override
    @Transactional
    public void deleteElectorById(Long id) {
        Elector elector = userServ.findElectorById(id);
        //take one vote away from a candidate when removing the elector
        if (elector.isVoted()==true){
            Candidate candidate = candidateDao.findById(elector.getCandidate().getId());
            candidate.getElectors().remove(elector);
            Long voices = candidate.getVoices()-1L;
            candidate.setVoices(voices);
            candidateDao.save(candidate);
            elector.setCandidate(null);
        }
        try{
            electorDao.delete(id);
        }catch (NoResultException nre){}   
    }

    
    @Transactional
    @Override
    public void saveCandidateDetails(CandidateDetails details) {
        detailsDao.save(details);
    }
    
    @Transactional 
    @Override
    public Admin getAdmin(Long id){
        return adminDao.getAdmin(id);
    }
    
    @Transactional
    @Override
    public void saveAdmin (Admin admin){
       adminDao.save(admin);
    }
    
    
}
