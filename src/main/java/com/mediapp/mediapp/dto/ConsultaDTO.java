package com.mediapp.mediapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediapp.mediapp.model.DetalleConsulta;
import com.mediapp.mediapp.model.Especialidad;
import com.mediapp.mediapp.model.Medico;
import com.mediapp.mediapp.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {

    private Integer idConsulta;
    @NotNull
    private String numConsultorio;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime fecha;
    @NotNull
    private PacienteDTO pacienteDTO; // composicion o hacia un valo
    @NotNull
    private MedicoDTO medicoDTO;
    @NotNull
    private Especialidad especialidad;
    @NotNull
    private List<DetalleConsultaDTO> detalleConsultas;

}
