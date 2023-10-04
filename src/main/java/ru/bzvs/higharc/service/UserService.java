package ru.bzvs.higharc.service;


import ru.bzvs.higharc.dto.UserDto;

import java.util.List;

public interface UserService {

    Long create(UserDto dto);

    UserDto find(Long id);

    List<UserDto> findByFirstAndLastNames(String firstName, String lastName);
}
