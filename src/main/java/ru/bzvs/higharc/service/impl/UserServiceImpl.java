package ru.bzvs.higharc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder encoder;

    @Override
    public UserDto create(UserDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        return mapper.entityToUserDto(repository.save(mapper.dtoToUserEntity(dto)));
    }

    @Override
    public UserDto find(Long id) {
        return mapper.entityToUserDto(repository.findById(id).orElseThrow());
    }
}
