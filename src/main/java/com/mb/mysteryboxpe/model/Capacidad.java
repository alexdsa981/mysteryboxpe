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
public class Capacidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacidad;
    private String nombre;

    @OneToMany(mappedBy = "capacidad")
    private List<BoxPlantilla> ListaPlantillas;

}
