package com.example.TurismoVuelaAlto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destino")
@Getter
@Setter
@NoArgsConstructor
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

}