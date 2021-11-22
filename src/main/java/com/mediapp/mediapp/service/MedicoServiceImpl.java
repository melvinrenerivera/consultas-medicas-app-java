package com.mediapp.mediapp.service;

import com.mediapp.mediapp.model.Medico;
import com.mediapp.mediapp.repo.IGenericRepo;
import com.mediapp.mediapp.repo.MedicoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl extends CRUDImpl<Medico,Integer> implements MedicoService{

    @Autowired
    private MedicoRepo medicoRepo;
    @Override
    protected IGenericRepo<Medico, Integer> getRepo() {
        return medicoRepo;
    }
}
