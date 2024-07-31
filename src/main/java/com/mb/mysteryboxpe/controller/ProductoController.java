package com.mb.mysteryboxpe.controller;

import com.mb.mysteryboxpe.dto.*;
import com.mb.mysteryboxpe.model.Categoria;
import com.mb.mysteryboxpe.model.Plantilla;
import com.mb.mysteryboxpe.model.Producto;
import com.mb.mysteryboxpe.model.Rareza;
import com.mb.mysteryboxpe.repository.CategoriaRepository;
import com.mb.mysteryboxpe.repository.ProductoRepository;
import com.mb.mysteryboxpe.repository.RarezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final RarezaRepository rarezaRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProductoController(ProductoRepository productoRepository, RarezaRepository rarezaRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.rarezaRepository = rarezaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<ProductoRecord> crearProducto(@RequestBody CrearProductoRecord crearProductoRecord) {
        Rareza rareza = rarezaRepository.findById(crearProductoRecord.idRareza()).orElseThrow();
        Categoria categoria = categoriaRepository.findById(crearProductoRecord.idCategoria()).orElseThrow();

        Producto producto = new Producto();
        producto.setCategoria(categoria);
        producto.setRareza(rareza);
        producto.setNombre(crearProductoRecord.nombre());
        producto.setPrecio(crearProductoRecord.precio());
        producto.setStock(crearProductoRecord.stock());

        producto = productoRepository.save(producto);

        ProductoRecord productoRecord = new ProductoRecord(producto.getId(),producto.getNombre(),producto.getStock(),producto.getPrecio(),new RarezaRecord(rareza), new CategoriaRecord(categoria));

        return new ResponseEntity<>(productoRecord, HttpStatus.CREATED);
    }

    //READ
    @GetMapping("/{id}")
    public ResponseEntity<ProductoRecord> obtenerProducto(@PathVariable Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            Producto valorProducto = producto.get();
            ProductoRecord productoRecord = new ProductoRecord(valorProducto);
            return ResponseEntity.ok(productoRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoRecord>> obtenerTodasLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoRecord> listaProductosRecord = productos.stream()
                .map(producto -> new ProductoRecord(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getStock(),
                        producto.getPrecio(),
                        new RarezaRecord(producto.getRareza()),
                        new CategoriaRecord(producto.getCategoria())
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaProductosRecord);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductoRecord> actualizarPlantilla(@PathVariable Long id, @RequestBody CrearProductoRecord actualizarProductoRecord) {

        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        producto.setNombre(actualizarProductoRecord.nombre());
        producto.setStock(actualizarProductoRecord.stock());
        producto.setPrecio(actualizarProductoRecord.precio());
        producto.setCategoria(categoriaRepository.findById(actualizarProductoRecord.idCategoria()).orElseThrow());
        producto.setRareza(rarezaRepository.findById(actualizarProductoRecord.idRareza()).orElseThrow());

        Producto productoAtualizado = productoRepository.save(producto);

        ProductoRecord productoAtualizadoRecord = new ProductoRecord(productoAtualizado);
        return ResponseEntity.ok(productoAtualizadoRecord);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
