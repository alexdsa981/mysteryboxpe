package com.mb.mysteryboxpe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departamento;
    private String distrito;
    private String direccion;
    private String referencias;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
