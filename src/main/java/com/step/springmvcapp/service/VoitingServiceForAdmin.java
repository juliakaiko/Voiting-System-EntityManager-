package com.step.springmvcapp.service;

import com.step.springmvcapp.entity.Admin;
import com.step.springmvcapp.entity.Candidate;
import com.step.springmvcapp.entity.CandidateDetails;
import com.step.springmvcapp.entity.Elector;
import java.util.Collection;

public interface VoitingServiceForAdmin {  
    void saveCandidate (Candidate candidate);    
    void addAllCandidates (Collection <Candidate> candidates);    
    void deleteCandidateById (Long id);
    void saveElector(Elector elector);
    void addAllElectors (Collection <Elector> electors);
    void deleteElectorById (Long id);        
    void saveCandidateDetails(CandidateDetails details);
    Admin getAdmin(Long id);
    void saveAdmin (Admin admin); 
}
