package com.step.springmvcapp.service;

import com.step.springmvcapp.entity.Candidate;
import com.step.springmvcapp.entity.CandidateDetails;
import com.step.springmvcapp.entity.Elector;
import java.util.List;

public interface VoitingServiceForUser {
    List <Candidate> findCandidates();
    Candidate findCandidateById(Long id);        
    List <Elector> findElectors();
    Elector findElectorById(Long id);
    void vote (Long electorId, Long candidateId);
    String hashPassword (String password);
    CandidateDetails findCandidateDetailsById(Long id);
}
