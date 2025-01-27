package com.espe.productos.controllers;

import com.espe.productos.models.Producto;
import com.espe.productos.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Producto> producto = service.findById(id);
        return producto.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Producto producto, @PathVariable Long id) {
        Optional<Producto> optionalProducto = service.findById(id);
        if (optionalProducto.isPresent()) {
            Producto productoDB = optionalProducto.get();
            productoDB.setNombre(producto.getNombre());
            productoDB.setDescripcion(producto.getDescripcion());
            productoDB.setPrecio(producto.getPrecio());
            productoDB.setStock(producto.getStock());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Producto> producto = service.findById(id);
        if (producto.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
