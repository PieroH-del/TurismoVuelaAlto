package com.example.TurismoVuelaAlto.service.impl;

import com.example.TurismoVuelaAlto.dto.ActividadDTO;
import com.example.TurismoVuelaAlto.entity.ActividadEntity;
import com.example.TurismoVuelaAlto.entity.DestinoEntity;
import com.example.TurismoVuelaAlto.exception.ResourceNotFoundException;
import com.example.TurismoVuelaAlto.mapper.ActividadMapper;
import com.example.TurismoVuelaAlto.repository.ActividadRepository;
import com.example.TurismoVuelaAlto.repository.DestinoRepository;
import com.example.TurismoVuelaAlto.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadServiceImpl implements ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private DestinoRepository destinoRepository;

    @Autowired
    private ActividadMapper actividadMapper;

    @Override
    public ActividadDTO guardar(ActividadDTO actividad) {
        ActividadEntity entity = actividadMapper.toEntity(actividad);

        // Asignar el destino
        DestinoEntity destino = destinoRepository.findById(actividad.getIdDestino().longValue())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Destino no encontrado con ID: " + actividad.getIdDestino()));
        entity.setDestino(destino);
        entity.setEstadoActividad("A"); // Por defecto activo

        ActividadEntity saved = actividadRepository.save(entity);
        return actividadMapper.toDTO(saved);
    }

    @Override
    public List<ActividadDTO> listarPorDestino(Long idDestino) {
        List<ActividadEntity> entities = actividadRepository.findByDestino_IdDestino(idDestino);
        return actividadMapper.toDTOList(entities);
    }

    @Override
    public List<ActividadDTO> listarActivasPorDestino(Long idDestino) {
        List<ActividadEntity> entities = actividadRepository.findByDestino_IdDestinoAndEstadoActividad(idDestino, "A");
        return actividadMapper.toDTOList(entities);
    }

    @Override
    public Optional<ActividadDTO> buscarPorId(Long id) {
        return actividadRepository.findById(id)
                .map(actividadMapper::toDTO);
    }

    @Override
    public ActividadDTO actualizar(ActividadDTO actividad) {
        if (actividad.getIdActividad() == null) {
            throw new ResourceNotFoundException("El ID de la actividad no puede ser nulo");
        }

        ActividadEntity entity = actividadMapper.toEntity(actividad);

        // Asignar el destino
        DestinoEntity destino = destinoRepository.findById(actividad.getIdDestino().longValue())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Destino no encontrado con ID: " + actividad.getIdDestino()));
        entity.setDestino(destino);

        ActividadEntity updated = actividadRepository.save(entity);
        return actividadMapper.toDTO(updated);
    }

    @Override
    public void inactivar(Long id) {
        ActividadEntity actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada con ID: " + id));

        actividad.setEstadoActividad("I");
        actividadRepository.save(actividad);
    }

    @Override
    public List<ActividadDTO> listarTodas() {
        List<ActividadEntity> entities = actividadRepository.findAll();
        return actividadMapper.toDTOList(entities);
    }

    @Override
    public List<ActividadDTO> listarActivas() {
        List<ActividadEntity> entities = actividadRepository.findByEstadoActividad("A");
        return actividadMapper.toDTOList(entities);
    }

}
