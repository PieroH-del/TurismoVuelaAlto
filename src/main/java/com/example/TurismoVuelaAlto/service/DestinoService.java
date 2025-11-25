package com.example.TurismoVuelaAlto.service;

import com.example.TurismoVuelaAlto.entity.DestinoEntity;

import java.util.List;
import java.util.Optional;

public interface DestinoService {

    // RF10.1 - Registrar destino
    DestinoEntity guardar(DestinoEntity destino);

    // RF10.2 - Listar destinos
    List<DestinoEntity> listarTodos();

    List<DestinoEntity> listarActivos();

    // RF10.3 - Editar destino
    Optional<DestinoEntity> buscarPorId(Long id);

    DestinoEntity actualizar(DestinoEntity destino);

    // RF10.4 - Inactivar destino
    void inactivar(Long id);

    // RF10.9 - Buscar por nombre
    List<DestinoEntity> buscarPorNombre(String nombre);

    // RF10.10 - Detalle con actividades
    Optional<DestinoEntity> obtenerConActividades(Long id);

}
