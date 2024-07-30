package com.mb.mysteryboxpe.dto;

import com.mb.mysteryboxpe.model.Categoria;

public record CategoriaRecord(
        Long id,
        String nombre
) {
    public CategoriaRecord(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNombre());
    }
}
