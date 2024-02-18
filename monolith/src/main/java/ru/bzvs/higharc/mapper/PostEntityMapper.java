package ru.bzvs.higharc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.bzvs.higharc.dto.PostDto;
import ru.bzvs.higharc.entity.PostEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostEntityMapper extends BaseMapper<PostEntity, PostDto> {
}
