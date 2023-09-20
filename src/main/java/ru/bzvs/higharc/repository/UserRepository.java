package ru.bzvs.higharc.repository;

import org.springframework.stereotype.Repository;
import ru.bzvs.higharc.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository {

    Long save(UserEntity entity);

    Optional<UserEntity> findById(Long id);
}
