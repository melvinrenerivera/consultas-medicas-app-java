package com.mediapp.mediapp.service;

import com.mediapp.mediapp.model.Paciente;
import com.mediapp.mediapp.repo.IGenericRepo;
import com.mediapp.mediapp.repo.PacienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl extends CRUDImpl<Paciente,Integer> implements PacienteService{

    @Autowired
    private PacienteRepo pacienteRepo;


    @Override
    protected IGenericRepo<Paciente, Integer> getRepo() {
        return pacienteRepo;
    }
}
