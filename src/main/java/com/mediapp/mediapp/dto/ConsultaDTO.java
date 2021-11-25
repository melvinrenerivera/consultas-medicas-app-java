package com.mediapp.mediapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private EspecialidadDTO especialidadDTO;
    @NotNull
    private List<DetalleConsultaDTO> detalleConsultas;

}
