package com.mb.mysteryboxpe.controller;

import com.mb.mysteryboxpe.model.Capacidad;
import com.mb.mysteryboxpe.repository.CapacidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/capacidades")
public class CapacidadController {

    private final CapacidadRepository capacidadRepository;

    @Autowired
    public CapacidadController(CapacidadRepository capacidadRepository) {
        this.capacidadRepository = capacidadRepository;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Capacidad> crearCapacidad(@RequestBody Capacidad capacidad) {
        Capacidad nuevaCapacidad = capacidadRepository.save(capacidad);
        return new ResponseEntity<>(nuevaCapacidad, HttpStatus.CREATED);
    }

    //READ
    @GetMapping("/{id}")
    public ResponseEntity<Capacidad> obtenerCapacidad(@PathVariable Long id) {
        Optional<Capacidad> capacidad = capacidadRepository.findById(id);
        if (capacidad.isPresent()) {
            Capacidad valorCapacidad = capacidad.get();
            return ResponseEntity.ok(valorCapacidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Capacidad>> obtenerTodasLasCapacidades() {
        List<Capacidad> capacidades = capacidadRepository.findAll();
        return ResponseEntity.ok(capacidades);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Capacidad> actualizarCapacidad(@PathVariable Long id, @RequestBody Capacidad capacidad) {
        if (!capacidadRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        capacidad.setId(id);
        Capacidad capacidadAtualizada = capacidadRepository.save(capacidad);
        return ResponseEntity.ok(capacidadAtualizada);
    }
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCapacidad(@PathVariable Long id) {
        if (!capacidadRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        capacidadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}