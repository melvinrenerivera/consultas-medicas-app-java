package com.mediapp.mediapp.service;

import com.mediapp.mediapp.model.Consulta;
import com.mediapp.mediapp.model.Examen;

import java.util.List;


public interface ConsultaService extends ICRUD<Consulta,Integer> {
    Consulta registrarConsulta(Consulta consulta, List<Examen> examenes);
}
