package com.example.TurismoVuelaAlto.repository;

import com.example.TurismoVuelaAlto.entity.ActividadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<ActividadEntity, Long> {

    // RF10.6 - Listar actividades por destino
    List<ActividadEntity> findByDestino_IdDestino(Long idDestino);

    // RF10.6 - Listar actividades activas por destino
    List<ActividadEntity> findByDestino_IdDestinoAndEstadoActividad(Long idDestino, String estadoActividad);

    // Listar actividades activas
    List<ActividadEntity> findByEstadoActividad(String estadoActividad);

}
