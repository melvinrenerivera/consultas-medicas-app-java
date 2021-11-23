package com.mediapp.mediapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ConsultaListaExamenDTO {

    @NotNull
    private ConsultaDTO consulta;
    @NotNull
    private List<ExamenDTO> lstExamen;
}
