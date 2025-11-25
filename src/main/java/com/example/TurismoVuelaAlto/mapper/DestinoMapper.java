package com.example.TurismoVuelaAlto.mapper;

import com.example.TurismoVuelaAlto.dto.DestinoDTO;
import com.example.TurismoVuelaAlto.entity.DestinoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DestinoMapper {

    // Convertir de Entity a DTO (sin actividades para evitar referencias
    // circulares)
    DestinoDTO toDTO(DestinoEntity entity);

    // Convertir de DTO a Entity
    @Mapping(target = "actividades", ignore = true)
    DestinoEntity toEntity(DestinoDTO dto);

    // Convertir listas
    List<DestinoDTO> toDTOList(List<DestinoEntity> entities);
}
