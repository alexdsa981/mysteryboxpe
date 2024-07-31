package com.mb.mysteryboxpe.dto;

public record CrearProductoRecord(
        Long id,
        String nombre,
        Double precio,
        Integer stock,
        Long idCategoria,
        Long idRareza
) {
}
