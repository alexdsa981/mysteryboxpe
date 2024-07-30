package com.mb.mysteryboxpe.dto;

public record crearPlantillaRecord(
        Long id,
        String nombre,
        Double precio,
        Long idCategoria,
        Long idCapacidad
) {
}
