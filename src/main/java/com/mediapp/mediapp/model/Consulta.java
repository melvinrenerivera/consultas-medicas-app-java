package com.mediapp.mediapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Consulta {

    @Id
    @Column(name = "idConsulta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsulta;


    @Column(name = "num_consultorio",length = 3,nullable = true)
    private String numConsultorio;

    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha;

    @ManyToOne //
    @JoinColumn(name = "id_paciente")
    private Paciente paciente; // composicion o hacia un valor

    @ManyToOne
    @JoinColumn(name = "id_medico")  //llave foranea
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_especialidad",foreignKey = @ForeignKey(name = "FK_consulta_paciente"))
    private Especialidad especialidad;

    //mappedBy se refiere al campo foraneo
    @OneToMany(mappedBy = "consulta",cascade = {CascadeType.ALL}, orphanRemoval = true )
    private List<DetalleConsulta> detalleConsultas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return idConsulta.equals(consulta.idConsulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConsulta);
    }
}
