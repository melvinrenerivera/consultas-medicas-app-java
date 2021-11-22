package com.mediapp.mediapp.service;

import com.mediapp.mediapp.model.Examen;
import com.mediapp.mediapp.repo.ExamenRepo;
import com.mediapp.mediapp.repo.IGenericRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamenServiceImpl extends CRUDImpl<Examen,Integer> implements ExamenService{

    @Autowired
    private ExamenRepo examenRepo;
    @Override
    protected IGenericRepo<Examen, Integer> getRepo() {
        return examenRepo;
    }
}
