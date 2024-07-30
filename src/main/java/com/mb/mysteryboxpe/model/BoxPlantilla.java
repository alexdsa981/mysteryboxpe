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
public class BoxPlantilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePlantilla;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "id_capacidad")
    private Capacidad capacidad;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "boxPlantilla")
    private List<BoxVenta> ListaBoxVenta;

}
