package com.mediapp.mediapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleConsultaDTO {

    private Integer idDetalleConsulta;
    @NotNull
    private String diagnostico;
    @NotNull
    private String tratamiento;
    @JsonIgnore
    private ConsultaDTO consultaDTO;


}
