package com.mediapp.mediapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Examen {

    @Id
    @Column(name = "idExamen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExamen;
    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;
    @Column(name = "descripcion", nullable = false,length = 150)
    private String descripcion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Examen examen = (Examen) o;
        return idExamen.equals(examen.idExamen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExamen);
    }
}
