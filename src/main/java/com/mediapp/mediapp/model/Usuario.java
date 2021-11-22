package com.mediapp.mediapp.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Usuario{

    @Id
    private Integer idUsuario;

    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",joinColumns = @JoinColumn(name = "id_usuario",referencedColumnName = "idUsuario"),
    inverseJoinColumns = @JoinColumn(name = "id_rol",referencedColumnName = "idRol"))
    private List<Rol> roles;

}
