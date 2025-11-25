package com.example.TurismoVuelaAlto.service.impl;

import com.example.TurismoVuelaAlto.entity.ActividadEntity;
import com.example.TurismoVuelaAlto.repository.ActividadRepository;
import com.example.TurismoVuelaAlto.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadServiceImpl implements ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Override
    public ActividadEntity guardar(ActividadEntity actividad) {
        actividad.setEstadoActividad("A"); // Por defecto activo
        return actividadRepository.save(actividad);
    }

    @Override
    public List<ActividadEntity> listarPorDestino(Long idDestino) {
        return actividadRepository.findByDestino_IdDestino(idDestino);
    }

    @Override
    public List<ActividadEntity> listarActivasPorDestino(Long idDestino) {
        return actividadRepository.findByDestino_IdDestinoAndEstadoActividad(idDestino, "A");
    }

    @Override
    public Optional<ActividadEntity> buscarPorId(Long id) {
        return actividadRepository.findById(id);
    }

    @Override
    public ActividadEntity actualizar(ActividadEntity actividad) {
        return actividadRepository.save(actividad);
    }

    @Override
    public void inactivar(Long id) {
        Optional<ActividadEntity> actividad = actividadRepository.findById(id);
        if (actividad.isPresent()) {
            ActividadEntity a = actividad.get();
            a.setEstadoActividad("I");
            actividadRepository.save(a);
        }
    }

    @Override
    public List<ActividadEntity> listarTodas() {
        return actividadRepository.findAll();
    }

    @Override
    public List<ActividadEntity> listarActivas() {
        return actividadRepository.findByEstadoActividad("A");
    }

}
