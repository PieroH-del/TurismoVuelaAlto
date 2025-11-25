package com.example.TurismoVuelaAlto.service;

import com.example.TurismoVuelaAlto.entity.ActividadEntity;

import java.util.List;
import java.util.Optional;

public interface ActividadService {

    // RF10.5 - Registrar actividad
    ActividadEntity guardar(ActividadEntity actividad);

    // RF10.6 - Listar actividades por destino
    List<ActividadEntity> listarPorDestino(Long idDestino);

    List<ActividadEntity> listarActivasPorDestino(Long idDestino);

    // RF10.7 - Editar actividad
    Optional<ActividadEntity> buscarPorId(Long id);

    ActividadEntity actualizar(ActividadEntity actividad);

    // RF10.8 - Inactivar actividad
    void inactivar(Long id);

    // Listar todas
    List<ActividadEntity> listarTodas();

    List<ActividadEntity> listarActivas();

}
