package com.example.TurismoVuelaAlto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "actividad")
@Getter
@Setter
@NoArgsConstructor

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

}
