package com.espe.categorias.services;

import com.espe.categorias.models.entities.Categoria;
import com.espe.categorias.models.entities.ProductoCategoria;

import java.util.List;
import java.util.Optional;


public interface CategoriasService {

    List<Categoria> findAll();

    Optional<Categoria> findById(Long id);

    Categoria save(Categoria categoria);

    void deleteById(Long id);

    Optional<ProductoCategoria> addProductToCategory(ProductoCategoria productoCategoria, Long categoriaId);
}
