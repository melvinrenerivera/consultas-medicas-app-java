package com.mediapp.mediapp.repo;

import com.mediapp.mediapp.model.Examen;
import com.mediapp.mediapp.model.Medico;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenRepo extends IGenericRepo<Examen,Integer> {
}
