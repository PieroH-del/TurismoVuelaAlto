package com.example.TurismoVuelaAlto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destino")
public class DestinoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destino")
    private Long idDestino;

    @NotBlank(message = "El nombre del destino es obligatorio")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombreDestino;

    @Column(name = "descripcion", length = 500)
    private String descripcionDestino;

    @Pattern(regexp = "[AI]", message = "El estado debe ser A (Activo) o I (Inactivo)")
    @Column(name = "estado", nullable = false, length = 1)
    private String estadoDestino = "A";

    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActividadEntity> actividades = new ArrayList<>();

    // Constructores
    public DestinoEntity() {
    }

    public DestinoEntity(Long idDestino, String nombreDestino, String descripcionDestino, String estadoDestino,
            List<ActividadEntity> actividades) {
        this.idDestino = idDestino;
        this.nombreDestino = nombreDestino;
        this.descripcionDestino = descripcionDestino;
        this.estadoDestino = estadoDestino;
        this.actividades = actividades;
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

    public List<ActividadEntity> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadEntity> actividades) {
        this.actividades = actividades;
    }
}