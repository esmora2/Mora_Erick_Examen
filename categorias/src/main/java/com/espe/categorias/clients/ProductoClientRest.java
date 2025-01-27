package com.espe.categorias.clients;

import com.espe.categorias.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productos", url = "productos:8004/api/productos")
public interface ProductoClientRest {

    @GetMapping("/{id}")
    Producto findById(@PathVariable Long id);
}
