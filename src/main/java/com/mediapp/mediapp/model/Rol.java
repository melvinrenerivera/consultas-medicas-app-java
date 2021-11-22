package com.mediapp.mediapp.model;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Rol {
    @Id
    private Integer idRol;
    @Column(name = "nombre",nullable = false,length = 15)
    private String nombre;
    @Column(name = "descripcion",nullable = false,length = 150)
    private String descripcion;
}
