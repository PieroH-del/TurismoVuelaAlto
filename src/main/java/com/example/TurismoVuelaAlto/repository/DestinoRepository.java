package com.example.TurismoVuelaAlto.repository;

import com.example.TurismoVuelaAlto.entity.DestinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinoRepository extends JpaRepository<DestinoEntity, Long> {

    // RF10.2 - Listar destinos activos
    List<DestinoEntity> findByEstadoDestino(String estadoDestino);

    // RF10.9 - Buscar destinos por nombre
    List<DestinoEntity> findByNombreDestinoContainingIgnoreCase(String nombreDestino);

    // RF10.10 - Detalle destino con actividades
    @Query("SELECT d FROM DestinoEntity d LEFT JOIN FETCH d.actividades WHERE d.idDestino = :id")
    Optional<DestinoEntity> findByIdWithActividades(@Param("id") Long idDestino);

    // RNF10.V1 - Validar nombre duplicado al registrar
    boolean existsByNombreDestino(String nombreDestino);

    // RNF10.V1 - Validar nombre duplicado al editar
    boolean existsByNombreDestinoAndIdDestinoNot(String nombreDestino, Long idDestino);

}
