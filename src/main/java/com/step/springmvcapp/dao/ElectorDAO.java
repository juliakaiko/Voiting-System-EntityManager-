package com.step.springmvcapp.dao;

import com.step.springmvcapp.entity.Elector;
import java.util.Collection;
import java.util.List;

public interface ElectorDAO {
    Elector findById (Long id);
    List <Elector> findAll();
    void save (Elector obj);
    void saveAll (Collection <Elector> obj);
    void delete (Long id);
}
