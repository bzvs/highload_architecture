package ru.bzvs.higharc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.mapper.UserEntityMapper;
import ru.bzvs.higharc.repository.UserRepository;
import ru.bzvs.higharc.service.UserService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserEntityMapper mapper;

    private final PasswordEncoder encoder;

    @Override
    public Long create(UserDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        return repository.save(mapper.dtoToEntity(dto));
    }

    @Override
    public UserDto find(Long id) {
        return mapper.entityToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<UserDto> findByFirstAndLastNames(String firstName, String lastName) {
        return mapper.mapToDtos(repository.findByFirstNameAndSecondName(firstName, lastName));
    }

    @Override
    public void addFriend(Long userId) {
        repository.saveFriend(extractCurrentUserId(), userId);
    }

    @Override
    public void deleteFriend(Long userId) {
        repository.deleteFriend(extractCurrentUserId(), userId);
    }

    @Override
    public Long extractCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication.getPrincipal()) ? ((UserDetailsImpl) authentication.getPrincipal()).getId() : null;
    }

    @Override
    public List<UserDto> findFriends(Long userId) {
        return mapper.mapToDtos(repository.findFriends(userId));
    }

    @Override
    public List<UserDto> findSubscribers(Long userId) {
        return mapper.mapToDtos(repository.findSubscribers(userId));
    }

    @Override
    public List<UserDto> findUsersWithFriendsAndPosts() {
        return mapper.mapToDtos(repository.findUsersWithFriendsAndPosts());
    }
}
