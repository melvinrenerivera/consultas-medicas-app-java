package com.mediapp.mediapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @Column(name = "idPaciente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;
    @Column(name = "nombres",nullable = false,length = 70)
    private String nombres;
    @Column(name = "apellidos",nullable = false,length = 70)
    private String apellidos;
    @Column(name = "dni",nullable = false,length = 8)
    private String dni;
    @Column(name = "direccion",nullable = false,length = 150)
    private String direccion;
    @Column(name = "telefono",nullable = false,length = 9)
    private String telefono;
    @Column(name = "email",nullable = false,length = 55)
    private String email;
}
