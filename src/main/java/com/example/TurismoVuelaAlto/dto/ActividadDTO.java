package com.example.TurismoVuelaAlto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

public class ActividadDTO {

    private Long idActividad;

    @NotBlank(message = "El nombre de la actividad es obligatorio")
    private String nombreActividad;

    @NotNull(message = "El precio debe ser numérico")
    @Min(value = 1, message = "El precio debe ser mayor que cero")
    private BigDecimal precio;

    @NotNull(message = "La duración debe ser un número positivo")
    @Min(value = 1, message = "La duración debe ser al menos 1 hora")
    private Integer duracion;

    @Pattern(regexp = "^[AI]$", message = "El estado debe ser A (Activo) o I (Inactivo)")
    private String estado;

    @NotNull(message = "No se permite una actividad sin destino")
    private Integer idDestino;

    private String nombreDestino;

}