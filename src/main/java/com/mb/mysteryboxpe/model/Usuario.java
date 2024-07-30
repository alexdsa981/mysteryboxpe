package com.mb.mysteryboxpe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private String username;
    private String contraseña;

    @OneToMany(mappedBy = "usuario")
    private List<Direccion> ListaDirecciones;

    @OneToMany(mappedBy = "usuario")
    private List<Boleta> ListaBoletas;

}
