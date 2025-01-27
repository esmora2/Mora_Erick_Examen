package com.espe.categorias.controllers;

import com.espe.categorias.models.entities.Categoria;
import com.espe.categorias.models.entities.ProductoCategoria;
import com.espe.categorias.services.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriasService service;

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(categoria));
    }

    // Listar todas las categorías
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    // Buscar categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = service.findById(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok().body(categoriaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Editar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Categoria categoria, @PathVariable Long id) {
        Optional<Categoria> categoriaOptional = service.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoriaDB = categoriaOptional.get();
            categoriaDB.setNombre(categoria.getNombre());
            categoriaDB.setDescripcion(categoria.getDescripcion());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(categoriaDB));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Categoria> categoria = service.findById(id);
        if (categoria.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada para eliminar");
        }
    }

    // Asignar un producto a una categoría
    @PostMapping("/{id}/productos")
    public ResponseEntity<?> asignarProducto(@RequestBody ProductoCategoria productoCategoria, @PathVariable Long id) {
        try {
            Optional<ProductoCategoria> optionalProductoCategoria = service.addProductToCategory(productoCategoria, id);
            if (optionalProductoCategoria.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(optionalProductoCategoria.get());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("Error", "No se pudo asignar el producto a la categoría. " + e.getMessage()));
        }
        return ResponseEntity.notFound().build();
    }
}
