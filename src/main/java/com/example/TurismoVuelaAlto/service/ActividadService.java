package com.example.TurismoVuelaAlto.service;

import com.example.TurismoVuelaAlto.dto.ActividadDTO;

import java.util.List;
import java.util.Optional;

public interface ActividadService {

    //Registrar actividad
    ActividadDTO guardar(ActividadDTO actividad);

    //Listar actividades por destino
    List<ActividadDTO> listarPorDestino(Long idDestino);

    List<ActividadDTO> listarActivasPorDestino(Long idDestino);

    //Editar actividad
    Optional<ActividadDTO> buscarPorId(Long id);

    ActividadDTO actualizar(ActividadDTO actividad);

    //Inactivar actividad
    void inactivar(Long id);

    //Activar actividad
    void activar(Long id);

    //Listar todas
    List<ActividadDTO> listarTodas();

    List<ActividadDTO> listarActivas();

}
