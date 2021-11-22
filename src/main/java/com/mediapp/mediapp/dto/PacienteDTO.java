package com.mediapp.mediapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "paciente DTO Data")
public class PacienteDTO {

    private Integer idPaciente;

    @Schema(description = "nombre del paciente")
    @NotEmpty
    @Size(min = 3, message = "{nombres.size}")
    private String nombres;

    @Schema(description = "apellido del paciente")
    @NotNull
    @Size(min = 3, message = "{apellidos.size}")
    private String apellidos;

    @Size(min = 3)
    private String dni;
    @Size(min = 3, max = 150)
    private String direccion;
    //@Pattern(regexp = "asdfsdf")
    @Size(min = 3)
    private String telefono;
    @Email
    private String email;

}
