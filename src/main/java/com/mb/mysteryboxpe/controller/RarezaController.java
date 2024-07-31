package com.mb.mysteryboxpe.controller;

import com.mb.mysteryboxpe.model.Rareza;
import com.mb.mysteryboxpe.repository.RarezaRepository;
import com.mb.mysteryboxpe.repository.RarezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rarezas")
public class RarezaController {

    private final RarezaRepository rarezaRepository;

    @Autowired
    public RarezaController(RarezaRepository rarezaRepository) {
        this.rarezaRepository = rarezaRepository;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Rareza> crearRareza(@RequestBody Rareza Rareza) {
        Rareza nuevaRareza = rarezaRepository.save(Rareza);
        return new ResponseEntity<>(nuevaRareza, HttpStatus.CREATED);
    }

    //READ
    @GetMapping("/{id}")
    public ResponseEntity<Rareza> obtenerRareza(@PathVariable Long id) {
        Optional<Rareza> Rareza = rarezaRepository.findById(id);
        if (Rareza.isPresent()) {
            Rareza valorRareza = Rareza.get();
            return ResponseEntity.ok(valorRareza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Rareza>> obtenerTodasLasRarezaes() {
        List<Rareza> Rarezas = rarezaRepository.findAll();
        return ResponseEntity.ok(Rarezas);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Rareza> actualizarRareza(@PathVariable Long id, @RequestBody Rareza Rareza) {
        if (!rarezaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Rareza.setId(id);
        Rareza RarezaAtualizada = rarezaRepository.save(Rareza);
        return ResponseEntity.ok(RarezaAtualizada);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRareza(@PathVariable Long id) {
        if (!rarezaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        rarezaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}