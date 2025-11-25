package com.example.TurismoVuelaAlto.service;

import com.example.TurismoVuelaAlto.dto.DestinoDTO;
import com.example.TurismoVuelaAlto.entity.DestinoEntity;

import java.util.List;
import java.util.Optional;

public interface DestinoService {

    // RF10.1 - Registrar destino
    DestinoDTO guardar(DestinoDTO destino);

    // RF10.2 - Listar destinos
    List<DestinoDTO> listarTodos();

    List<DestinoDTO> listarActivos();

    // RF10.3 - Editar destino
    Optional<DestinoDTO> buscarPorId(Long id);

    DestinoDTO actualizar(DestinoDTO destino);

    // RF10.4 - Inactivar destino
    void inactivar(Long id);

    // RF10.9 - Buscar por nombre
    List<DestinoDTO> buscarPorNombre(String nombre);

    // RF10.10 - Detalle con actividades (mantener Entity para la vista)
    Optional<DestinoEntity> obtenerConActividades(Long id);

}
