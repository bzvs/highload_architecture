package ru.bzvs.higharc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.bzvs.higharc.dto.PostDto;
import ru.bzvs.higharc.entity.PostEntity;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostEntityMapper {

    PostEntity dtoToEntity(PostDto dto);

    PostDto entityToDto(PostEntity entity);

    List<PostDto> mapToDtos(Collection<PostEntity> entities);
}
