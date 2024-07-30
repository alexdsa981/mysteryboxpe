package com.mb.mysteryboxpe.controller;

import com.mb.mysteryboxpe.model.Capacidad;
import com.mb.mysteryboxpe.repository.CapacidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<Capacidad> crearCapacidad(@RequestBody Capacidad capacidad){
        Capacidad nuevaCapacidad = capacidadRepository.save(capacidad);
        return new ResponseEntity<>(nuevaCapacidad, HttpStatus.CREATED);
    }
    //READ
    //UPDATE
    //DELETE
}
