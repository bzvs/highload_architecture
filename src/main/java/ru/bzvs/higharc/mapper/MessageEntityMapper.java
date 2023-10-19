package ru.bzvs.higharc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.bzvs.higharc.dto.MessageDto;
import ru.bzvs.higharc.entity.MessageEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageEntityMapper extends BaseMapper<MessageEntity, MessageDto> {
}
