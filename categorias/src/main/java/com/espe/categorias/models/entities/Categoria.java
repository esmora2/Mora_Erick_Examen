package com.espe.categorias.models.entities;

import com.espe.categorias.models.Producto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "La descripción no puede estar vacía.")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres.")
    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoCategoria> productoCategorias = new ArrayList<>();

    @Transient
    private List<Producto> productos;

    public Categoria() {
        productoCategorias = new ArrayList<>();
        productos = new ArrayList<>();
    }

    // Métodos de utilidad para agregar y eliminar asociaciones
    public void addProductoCategoria(ProductoCategoria productoCategoria) {
        productoCategorias.add(productoCategoria);
    }

    public void removeProductoCategoria(ProductoCategoria productoCategoria) {
        productoCategorias.remove(productoCategoria);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ProductoCategoria> getProductoCategorias() {
        return productoCategorias;
    }

    public void setProductoCategorias(List<ProductoCategoria> productoCategorias) {
        this.productoCategorias = productoCategorias;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
