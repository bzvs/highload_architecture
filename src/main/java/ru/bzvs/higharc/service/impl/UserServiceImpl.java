package ru.bzvs.higharc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.mapper.UserEntityMapper;
import ru.bzvs.higharc.repository.UserRepository;
import ru.bzvs.higharc.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserEntityMapper mapper;

    @Override
    public UserDto create(UserDto dto) {
        return mapper.mapToDto(repository.save(mapper.mapToEntity(dto)));
    }

    @Override
    public UserDto find(Long id) {
        return mapper.mapToDto(repository.findById(id).orElseThrow());
    }
}
