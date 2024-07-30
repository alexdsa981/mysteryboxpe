package com.mb.mysteryboxpe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer stock;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "id_rareza")
    private Rareza rareza;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;


}
