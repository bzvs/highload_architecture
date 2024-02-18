package ru.bzvs.higharc.dialog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.bzvs.higharc.dialog.dto.MessageDto;
import ru.bzvs.higharc.dialog.entity.MessageEntity;
import ru.bzvs.higharc.mapper.BaseMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageEntityMapper extends BaseMapper<MessageEntity, MessageDto> {
}
