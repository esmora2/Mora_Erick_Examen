package com.espe.categorias.repositories;

import com.espe.categorias.models.entities.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
