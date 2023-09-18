package ru.bzvs.higharc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.entity.UserEntity;

@Mapper
public interface UserEntityMapper {

    UserEntity mapToEntity(UserDto dto);

    UserDto mapToDto(UserEntity entity);
}
