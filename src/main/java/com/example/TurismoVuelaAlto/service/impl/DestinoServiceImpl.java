package com.example.TurismoVuelaAlto.service.impl;

import com.example.TurismoVuelaAlto.dto.DestinoDTO;
import com.example.TurismoVuelaAlto.entity.DestinoEntity;
import com.example.TurismoVuelaAlto.exception.ResourceNotFoundException;
import com.example.TurismoVuelaAlto.mapper.DestinoMapper;
import com.example.TurismoVuelaAlto.repository.DestinoRepository;
import com.example.TurismoVuelaAlto.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoServiceImpl implements DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    @Autowired
    private DestinoMapper destinoMapper;

    @Override
    public DestinoDTO guardar(DestinoDTO destino) {
        DestinoEntity entity = destinoMapper.toEntity(destino);
        entity.setEstadoDestino("A"); // Por defecto activo
        DestinoEntity saved = destinoRepository.save(entity);
        return destinoMapper.toDTO(saved);
    }

    @Override
    public List<DestinoDTO> listarTodos() {
        List<DestinoEntity> entities = destinoRepository.findAll();
        return destinoMapper.toDTOList(entities);
    }

    @Override
    public List<DestinoDTO> listarActivos() {
        List<DestinoEntity> entities = destinoRepository.findByEstadoDestino("A");
        return destinoMapper.toDTOList(entities);
    }

    @Override
    public Optional<DestinoDTO> buscarPorId(Long id) {
        return destinoRepository.findById(id)
                .map(destinoMapper::toDTO);
    }

    @Override
    public DestinoDTO actualizar(DestinoDTO destino) {
        if (destino.getIdDestino() == null) {
            throw new ResourceNotFoundException("El ID del destino no puede ser nulo");
        }

        DestinoEntity entity = destinoMapper.toEntity(destino);
        DestinoEntity updated = destinoRepository.save(entity);
        return destinoMapper.toDTO(updated);
    }

    @Override
    public void inactivar(Long id) {
        DestinoEntity destino = destinoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + id));

        destino.setEstadoDestino("I");
        destinoRepository.save(destino);
    }

    @Override
    public List<DestinoDTO> buscarPorNombre(String nombre) {
        List<DestinoEntity> entities = destinoRepository.findByNombreDestinoContainingIgnoreCase(nombre);
        return destinoMapper.toDTOList(entities);
    }

    @Override
    public Optional<DestinoEntity> obtenerConActividades(Long id) {
        return destinoRepository.findByIdWithActividades(id);
    }

}
