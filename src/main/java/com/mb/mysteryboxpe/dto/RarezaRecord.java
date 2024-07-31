package com.mb.mysteryboxpe.dto;

import com.mb.mysteryboxpe.model.Producto;
import com.mb.mysteryboxpe.model.Rareza;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

public record RarezaRecord(
        Long id,
        String nombre,
        Double probabilidad
) {
    public RarezaRecord(Rareza rareza) {
        this(
                rareza.getId(),
                rareza.getNombre(),
                rareza.getProbabilidad());
    }
}
