package com.mediapp.mediapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Especialidad {

    @Id
    @Column(name = "idEspecilidad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecialidad;
    private String nombre;


}
