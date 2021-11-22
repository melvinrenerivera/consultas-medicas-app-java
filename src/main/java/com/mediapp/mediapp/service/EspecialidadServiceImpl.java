package com.mediapp.mediapp.service;

import com.mediapp.mediapp.model.Especialidad;
import com.mediapp.mediapp.repo.EspecialidadRepo;
import com.mediapp.mediapp.repo.IGenericRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadServiceImpl extends CRUDImpl<Especialidad,Integer> implements EspecialidadService{

    @Autowired
    private EspecialidadRepo especialidadRepo;
    @Override
    protected IGenericRepo<Especialidad, Integer> getRepo() {
        return especialidadRepo;
    }
}
