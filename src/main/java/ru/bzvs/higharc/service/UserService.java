package ru.bzvs.higharc.service;


import ru.bzvs.higharc.dto.UserDto;

public interface UserService {

    Long create(UserDto dto);

    UserDto find(Long id);
}
