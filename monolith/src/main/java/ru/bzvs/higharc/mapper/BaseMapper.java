package ru.bzvs.higharc.mapper;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<ENTITY, DTO> {

    ENTITY dtoToEntity(DTO dto);

    DTO entityToDto(ENTITY entity);

    List<DTO> mapToDtos(Collection<ENTITY> entities);
}
