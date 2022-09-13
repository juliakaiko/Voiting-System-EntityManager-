package com.step.springmvcapp.dao;

import com.step.springmvcapp.entity.Candidate;
import java.util.Collection;
import java.util.List;

public interface CandidateDAO {
    Candidate findById (Long id);
    List <Candidate> findAll();
    void save (Candidate obj);
    void saveAll (Collection <Candidate> obj);
    void delete (Long id);
}
