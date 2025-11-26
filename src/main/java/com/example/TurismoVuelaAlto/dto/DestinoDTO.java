package com.example.TurismoVuelaAlto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}