package ru.bzvs.higharc.service;


import ru.bzvs.higharc.dto.UserDto;

public interface UserService {

    UserDto create(UserDto dto);

    UserDto find(Long id);
}
