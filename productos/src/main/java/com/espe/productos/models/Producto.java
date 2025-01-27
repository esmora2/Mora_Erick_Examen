package com.espe.productos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "productos")
public class Producto {

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

    @Column(nullable = false)
    @NotNull(message = "El precio no puede estar vacío.")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0.")
    private Double precio;

    @Column(nullable = false)
    @NotNull(message = "El stock no puede estar vacío.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    private Integer stock;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    public Producto() {
        this.fechaCreacion = new Date();
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
