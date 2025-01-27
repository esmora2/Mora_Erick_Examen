package com.espe.productos.services;

import com.espe.productos.models.Producto;
import com.espe.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) repository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

