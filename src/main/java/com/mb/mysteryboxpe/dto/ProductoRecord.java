package com.mb.mysteryboxpe.dto;

import com.mb.mysteryboxpe.model.Categoria;
import com.mb.mysteryboxpe.model.Producto;
import com.mb.mysteryboxpe.model.Rareza;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record ProductoRecord(
        Long id,
        String nombre,
        Integer stock,
        Double precio,
        RarezaRecord rareza,
        CategoriaRecord categoria
) {
    public ProductoRecord(Producto producto){
        this(
                producto.getId(),
                producto.getNombre(),
                producto.getStock(),
                producto.getPrecio(),
                new RarezaRecord(producto.getRareza()),
                new CategoriaRecord(producto.getCategoria())
        );
    }
}
