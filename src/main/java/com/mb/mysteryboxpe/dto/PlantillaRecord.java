package com.mb.mysteryboxpe.dto;

import com.mb.mysteryboxpe.model.Plantilla;

public record PlantillaRecord(
        Long id,
        String nombre,
        Double precio,
        CapacidadRecord capacidad,
        CategoriaRecord categoria
) {
    public PlantillaRecord(Plantilla plantilla) {
        this(
                plantilla.getId(),
                plantilla.getNombre(),
                plantilla.getPrecio(),
                new CapacidadRecord(plantilla.getCapacidad()),
                new CategoriaRecord(plantilla.getCategoria())
        );
    }
}
