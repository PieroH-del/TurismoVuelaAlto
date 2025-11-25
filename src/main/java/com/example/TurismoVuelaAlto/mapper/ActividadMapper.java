package com.example.TurismoVuelaAlto.mapper;

import com.example.TurismoVuelaAlto.dto.ActividadDTO;
import com.example.TurismoVuelaAlto.entity.ActividadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActividadMapper {

    // Convertir de Entity a DTO
    @Mapping(target = "precio", source = "precioActividad")
    @Mapping(target = "duracion", source = "duracionActividad")
    @Mapping(target = "estado", source = "estadoActividad")
    @Mapping(target = "idDestino", source = "destino.idDestino")
    @Mapping(target = "nombreDestino", source = "destino.nombreDestino")
    ActividadDTO toDTO(ActividadEntity entity);

    // Convertir de DTO a Entity (el destino se asigna en el servicio)
    @Mapping(target = "precioActividad", source = "precio")
    @Mapping(target = "duracionActividad", source = "duracion")
    @Mapping(target = "estadoActividad", source = "estado")
    @Mapping(target = "destino", ignore = true)
    ActividadEntity toEntity(ActividadDTO dto);

    // Convertir listas
    List<ActividadDTO> toDTOList(List<ActividadEntity> entities);
}