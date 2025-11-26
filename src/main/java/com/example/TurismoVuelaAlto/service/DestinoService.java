package com.example.TurismoVuelaAlto.service;

import com.example.TurismoVuelaAlto.dto.DestinoDTO;
import com.example.TurismoVuelaAlto.entity.DestinoEntity;

import java.util.List;
import java.util.Optional;

public interface DestinoService {

    //Registrar destino
    DestinoDTO guardar(DestinoDTO destino);

    //Listar destinos
    List<DestinoDTO> listarTodos();

    List<DestinoDTO> listarActivos();

    //Editar destino
    Optional<DestinoDTO> buscarPorId(Long id);

    DestinoDTO actualizar(DestinoDTO destino);

    //Inactivar destino
    void inactivar(Long id);

    //Activar destino
    void activar(Long id);

    //Buscar por nombre
    List<DestinoDTO> buscarPorNombre(String nombre);

    //Detalle con actividades (mantener Entity para la vista)
    Optional<DestinoEntity> obtenerConActividades(Long id);

}
