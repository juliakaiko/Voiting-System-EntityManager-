package com.step.springmvcapp.dao;

import com.step.springmvcapp.entity.CandidateDetails;
import java.util.Collection;
import java.util.List;

public interface CandidateDetailsDAO {
    CandidateDetails findById (Long id);
    List <CandidateDetails> findAll();
    void save (CandidateDetails obj);
    void saveAll (Collection <CandidateDetails> obj);
    void delete (Long id);
}
