package com.example.TurismoVuelaAlto.service.impl;

import com.example.TurismoVuelaAlto.entity.DestinoEntity;
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

    @Override
    public DestinoEntity guardar(DestinoEntity destino) {
        destino.setEstadoDestino("A"); // Por defecto activo
        return destinoRepository.save(destino);
    }

    @Override
    public List<DestinoEntity> listarTodos() {
        return destinoRepository.findAll();
    }

    @Override
    public List<DestinoEntity> listarActivos() {
        return destinoRepository.findByEstadoDestino("A");
    }

    @Override
    public Optional<DestinoEntity> buscarPorId(Long id) {
        return destinoRepository.findById(id);
    }

    @Override
    public DestinoEntity actualizar(DestinoEntity destino) {
        return destinoRepository.save(destino);
    }

    @Override
    public void inactivar(Long id) {
        Optional<DestinoEntity> destino = destinoRepository.findById(id);
        if (destino.isPresent()) {
            DestinoEntity d = destino.get();
            d.setEstadoDestino("I");
            destinoRepository.save(d);
        }
    }

    @Override
    public List<DestinoEntity> buscarPorNombre(String nombre) {
        return destinoRepository.findByNombreDestinoContainingIgnoreCase(nombre);
    }

    @Override
    public Optional<DestinoEntity> obtenerConActividades(Long id) {
        return destinoRepository.findByIdWithActividades(id);
    }

}
