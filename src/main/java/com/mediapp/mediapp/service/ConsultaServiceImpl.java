package com.mediapp.mediapp.service;

import com.mediapp.mediapp.model.Consulta;
import com.mediapp.mediapp.model.Especialidad;
import com.mediapp.mediapp.model.Examen;
import com.mediapp.mediapp.repo.ConsultaExamenRepo;
import com.mediapp.mediapp.repo.ConsultaRepo;
import com.mediapp.mediapp.repo.EspecialidadRepo;
import com.mediapp.mediapp.repo.IGenericRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta,Integer> implements ConsultaService{

    @Autowired
    private ConsultaRepo consultaRepo;
    @Autowired
    private ConsultaExamenRepo consultaExamenRepo;
    @Override
    protected IGenericRepo<Consulta, Integer> getRepo() {
        return consultaRepo;
    }

    @Transactional
    @Override
    public Consulta registrarConsulta(Consulta consulta, List<Examen> examenes) {
        consulta.getDetalleConsultas().forEach(c -> c.setConsulta(consulta));
        consultaRepo.save(consulta);
        examenes.forEach(e -> consultaExamenRepo.registrar(consulta.getIdConsulta(),e.getIdExamen()));
        return consulta;
    }
}
