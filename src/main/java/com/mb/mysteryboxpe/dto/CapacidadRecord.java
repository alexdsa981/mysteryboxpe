package com.mb.mysteryboxpe.dto;

import com.mb.mysteryboxpe.model.Capacidad;

public record CapacidadRecord(
        Long id,
        Integer capacidad,
        String nombre
) {
    public CapacidadRecord(Capacidad capacidad) {
        this(
                capacidad.getId(),
                capacidad.getCapacidad(),
                capacidad.getNombre());
    }
}
