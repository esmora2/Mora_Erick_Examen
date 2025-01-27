package com.espe.categorias.models;

import jakarta.validation.constraints.*;
import java.util.Date;

public class Producto {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía.")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres.")
    private String descripcion;

    @NotNull(message = "El precio no puede estar vacío.")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0.")
    private Double precio;

    @NotNull(message = "El stock no puede estar vacío.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    private int stock;

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
