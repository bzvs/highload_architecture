package ru.bzvs.higharc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.entity.UserEntity;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {

    UserEntity dtoToUserEntity(UserDto dto);

    UserDto entityToUserDto(UserEntity entity);

    List<UserDto> mapToDtos(Collection<UserEntity> entities);
}
