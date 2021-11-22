package com.mediapp.mediapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {


    private Integer idMedico;
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String nombres;
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String apellidos;
    @Size(min = 3)
    private String cmp;
    private  String fotoUrl;


}
