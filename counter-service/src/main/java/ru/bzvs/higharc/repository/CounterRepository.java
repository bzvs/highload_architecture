package ru.bzvs.higharc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bzvs.higharc.entity.CounterEntity;

import java.util.Optional;

public interface CounterRepository extends JpaRepository<CounterEntity, Long> {

    Optional<CounterEntity> findByUserId(Long userId);
}
