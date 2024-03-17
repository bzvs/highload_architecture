package ru.bzvs.higharc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.bzvs.higharc.dto.CounterDto;
import ru.bzvs.higharc.entity.CounterEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageEntityMapper extends BaseMapper<CounterEntity, CounterDto> {
}
