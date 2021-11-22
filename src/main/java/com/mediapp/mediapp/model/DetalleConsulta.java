package com.mediapp.mediapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DetalleConsulta {

    @Id
    @Column(name = "idDetalleConsulta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleConsulta;
    @Column(name = "diagnostico",nullable = false)
    private String diagnostico;
    @Column(name = "tratamiento",nullable = false)
    private String tratamiento;

    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;
}
