package com.mb.mysteryboxpe.dto;

public record CrearPlantillaRecord(
        Long id,
        String nombre,
        Double precio,
        Long idCategoria,
        Long idCapacidad
) {
}
