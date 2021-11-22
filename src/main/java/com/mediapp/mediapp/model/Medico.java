package com.mediapp.mediapp.model;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
//@Table(name = "medico") optional
public class Medico {

    @Id
    @Column(name = "idMedico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedico;
    @Column(name = "nombres",nullable = false,length = 70)
    private String nombres;
    @Column(name = "apellidos",nullable = false,length = 70)
    private String apellidos;
    @Column(name = "cmp",nullable = false,length = 70,unique = true)
    private String cmp;
    @Column(name = "fotoUrl",nullable = true)
    private  String fotoUrl;

}
