package com.mediapp.mediapp.repo;

import com.mediapp.mediapp.model.Consulta;
import com.mediapp.mediapp.model.ConsultaExamen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ConsultaExamenRepo extends IGenericRepo<ConsultaExamen,Integer> {

    @Modifying
    @Query(value = "insert into consulta_examen(id_consulta,id_examen) values (:idConsulta, :idExamen) ",nativeQuery = true)
    Integer registrar(@Param("idConsulta") Integer idConsulta,@Param("idExamen") Integer idExamen);
}
