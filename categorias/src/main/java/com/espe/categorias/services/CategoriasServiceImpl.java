package com.espe.categorias.services;

import com.espe.categorias.models.entities.Categoria;
import com.espe.categorias.models.entities.ProductoCategoria;
import com.espe.categorias.repositories.CategoriaRepository;
import com.espe.categorias.repositories.ProductoCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriasServiceImpl implements CategoriasService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoCategoriaRepository productoCategoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Optional<ProductoCategoria> addProductToCategory(ProductoCategoria productoCategoria, Long categoriaId) {
        // Buscar la categoría por ID
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaId);

        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();

            // Copiar el valor de productoId para que sea efectivamente final
            Long productoId = productoCategoria.getProductoId();

            // Verificar si el producto ya está asociado a la categoría
            boolean exists = categoria.getProductoCategorias().stream()
                    .anyMatch(prodCat -> prodCat.getProductoId().equals(productoId));
            if (exists) {
                throw new IllegalArgumentException("El producto ya está asignado a esta categoría.");
            }

            // Crear y guardar la relación Producto-Categoría
            productoCategoria.setCategoria(categoria);
            productoCategoria = productoCategoriaRepository.save(productoCategoria);

            // Asociar Producto-Categoría a la categoría
            categoria.getProductoCategorias().add(productoCategoria);
            categoriaRepository.save(categoria);

            return Optional.of(productoCategoria);
        }

        throw new IllegalArgumentException("Categoría no encontrada.");
    }

}
