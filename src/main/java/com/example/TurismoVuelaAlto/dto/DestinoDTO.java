package com.example.TurismoVuelaAlto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class DestinoDTO {

    private Long idDestino;

    @NotBlank(message = "El nombre del destino es obligatorio")
    private String nombreDestino;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcionDestino;

    @Pattern(regexp = "^[AI]$", message = "El estado debe ser A (Activo) o I (Inactivo)")
    private String estadoDestino;

    // Constructor vacío
    public DestinoDTO() {
    }

    // Getters y Setters
    public Long getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Long idDestino) {
        this.idDestino = idDestino;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getDescripcionDestino() {
        return descripcionDestino;
    }

    public void setDescripcionDestino(String descripcionDestino) {
        this.descripcionDestino = descripcionDestino;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }
}