package com.mb.mysteryboxpe.controller;

import com.mb.mysteryboxpe.dto.CapacidadRecord;
import com.mb.mysteryboxpe.dto.CategoriaRecord;
import com.mb.mysteryboxpe.dto.PlantillaRecord;
import com.mb.mysteryboxpe.dto.CrearPlantillaRecord;
import com.mb.mysteryboxpe.model.Capacidad;
import com.mb.mysteryboxpe.model.Categoria;
import com.mb.mysteryboxpe.model.Plantilla;
import com.mb.mysteryboxpe.repository.CapacidadRepository;
import com.mb.mysteryboxpe.repository.CategoriaRepository;
import com.mb.mysteryboxpe.repository.PlantillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/plantillas")
public class PlantillaController {

    private final PlantillaRepository plantillaRepository;
    private final CapacidadRepository capacidadRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public PlantillaController(PlantillaRepository plantillaRepository, CapacidadRepository capacidadRepository, CategoriaRepository categoriaRepository) {
        this.plantillaRepository = plantillaRepository;
        this.capacidadRepository = capacidadRepository;
        this.categoriaRepository = categoriaRepository;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<PlantillaRecord> crearPlantilla(@RequestBody CrearPlantillaRecord crearPlantillaRecord) {

        Capacidad capacidad = capacidadRepository.findById(crearPlantillaRecord.idCapacidad()).orElseThrow();
        Categoria categoria = categoriaRepository.findById(crearPlantillaRecord.idCategoria()).orElseThrow();

        Plantilla plantilla = new Plantilla();
        plantilla.setCategoria(categoria);
        plantilla.setCapacidad(capacidad);
        plantilla.setPrecio(crearPlantillaRecord.precio());
        plantilla.setNombre(crearPlantillaRecord.nombre());

        plantilla = plantillaRepository.save(plantilla);

        PlantillaRecord datosPlantilla = new PlantillaRecord(
                plantilla.getId(),
                plantilla.getNombre(),
                plantilla.getPrecio(),
                new CapacidadRecord(plantilla.getCapacidad()),
                new CategoriaRecord(plantilla.getCategoria())
        );

        return new ResponseEntity<>(datosPlantilla, HttpStatus.CREATED);
    }

    //READ
    @GetMapping("/{id}")
    public ResponseEntity<PlantillaRecord> obtenerPlantilla(@PathVariable Long id) {
        Optional<Plantilla> Plantilla = plantillaRepository.findById(id);
        if (Plantilla.isPresent()) {
            Plantilla valorPlantilla = Plantilla.get();
            PlantillaRecord plantillaRecord = new PlantillaRecord(valorPlantilla);
            return ResponseEntity.ok(plantillaRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PlantillaRecord>> obtenerTodasLasPlantillas() {
        List<Plantilla> plantillas = plantillaRepository.findAll();
        List<PlantillaRecord> listaPlantillasRecord = plantillas.stream()
                .map(plantilla -> new PlantillaRecord(
                        plantilla.getId(),
                        plantilla.getNombre(),
                        plantilla.getPrecio(),
                        new CapacidadRecord(plantilla.getCapacidad().getId(), plantilla.getCapacidad().getCapacidad(), plantilla.getCapacidad().getNombre()),
                        new CategoriaRecord(plantilla.getCategoria().getId(), plantilla.getCategoria().getNombre())
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaPlantillasRecord);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<PlantillaRecord> actualizarPlantilla(@PathVariable Long id, @RequestBody CrearPlantillaRecord actualizarPlantillaRecord) {

        Plantilla plantilla = plantillaRepository.findById(id).orElse(null);
        if (plantilla == null) {
            return ResponseEntity.notFound().build();
        }
        plantilla.setNombre(actualizarPlantillaRecord.nombre());
        plantilla.setPrecio(actualizarPlantillaRecord.precio());
        plantilla.setCategoria(categoriaRepository.findById(actualizarPlantillaRecord.idCategoria()).orElseThrow());
        plantilla.setCapacidad(capacidadRepository.findById(actualizarPlantillaRecord.idCapacidad()).orElseThrow());

        Plantilla plantillaAtualizada = plantillaRepository.save(plantilla);

        PlantillaRecord plantillaActualizadaRecord = new PlantillaRecord(plantillaAtualizada);
        return ResponseEntity.ok(plantillaActualizadaRecord);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlantilla(@PathVariable Long id) {
        if (!plantillaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        plantillaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}