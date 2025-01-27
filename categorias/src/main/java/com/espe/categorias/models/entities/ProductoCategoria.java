package com.espe.categorias.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos_categorias", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"producto_id", "categoria_id"})
})
public class ProductoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    @NotNull(message = "El ID del producto no puede ser nulo.")
    private Long productoId;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnore
    @NotNull(message = "La categoría asociada no puede ser nula.")
    private Categoria categoria;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
