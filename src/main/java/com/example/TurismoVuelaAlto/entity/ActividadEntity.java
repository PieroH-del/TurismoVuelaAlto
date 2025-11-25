package com.example.TurismoVuelaAlto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

@Entity
@Table(name = "actividad")
public class ActividadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long idActividad;

    @NotBlank(message = "El nombre de la actividad es obligatorio")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombreActividad;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioActividad;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser mayor a 0")
    @Column(name = "duracion", nullable = false)
    private Integer duracionActividad;

    @Pattern(regexp = "[AI]", message = "El estado debe ser A (Activo) o I (Inactivo)")
    @Column(name = "estado", nullable = false, length = 1)
    private String estadoActividad = "A";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino", nullable = false)
    @NotNull(message = "Debe seleccionar un destino válido")
    private DestinoEntity destino;

    // Constructores
    public ActividadEntity() {
    }

    public ActividadEntity(Long idActividad, String nombreActividad, BigDecimal precioActividad,
            Integer duracionActividad, String estadoActividad, DestinoEntity destino) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.precioActividad = precioActividad;
        this.duracionActividad = duracionActividad;
        this.estadoActividad = estadoActividad;
        this.destino = destino;
    }

    // Getters y Setters
    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public BigDecimal getPrecioActividad() {
        return precioActividad;
    }

    public void setPrecioActividad(BigDecimal precioActividad) {
        this.precioActividad = precioActividad;
    }

    public Integer getDuracionActividad() {
        return duracionActividad;
    }

    public void setDuracionActividad(Integer duracionActividad) {
        this.duracionActividad = duracionActividad;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    public DestinoEntity getDestino() {
        return destino;
    }

    public void setDestino(DestinoEntity destino) {
        this.destino = destino;
    }
}
