package com.example.TurismoVuelaAlto.service;

import com.example.TurismoVuelaAlto.dto.ActividadDTO;

import java.util.List;
import java.util.Optional;

public interface ActividadService {

    // RF10.5 - Registrar actividad
    ActividadDTO guardar(ActividadDTO actividad);

    // RF10.6 - Listar actividades por destino
    List<ActividadDTO> listarPorDestino(Long idDestino);

    List<ActividadDTO> listarActivasPorDestino(Long idDestino);

    // RF10.7 - Editar actividad
    Optional<ActividadDTO> buscarPorId(Long id);

    ActividadDTO actualizar(ActividadDTO actividad);

    // RF10.8 - Inactivar actividad
    void inactivar(Long id);

    // Listar todas
    List<ActividadDTO> listarTodas();

    List<ActividadDTO> listarActivas();

}
