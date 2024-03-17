package ru.bzvs.higharc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bzvs.higharc.dto.CounterDto;
import ru.bzvs.higharc.dto.DecrementCountRequest;
import ru.bzvs.higharc.dto.IncrementCountRequest;
import ru.bzvs.higharc.entity.CounterEntity;
import ru.bzvs.higharc.mapper.MessageEntityMapper;
import ru.bzvs.higharc.repository.CounterRepository;
import ru.bzvs.higharc.service.CounterService;
import ru.bzvs.higharc.service.UserService;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounterServiceImpl implements CounterService {

    private final CounterRepository repository;

    private final UserService userService;

    private final MessageEntityMapper mapper;

    @Override
    @Transactional
    @CacheEvict(value = "counterCache", key = "#request.userId()")
    public CounterDto increment(IncrementCountRequest request) {
        Optional<CounterEntity> counterEntity = repository.findByUserId(request.userId());
        CounterEntity entityToSave;
        if (counterEntity.isEmpty()) {
            entityToSave = CounterEntity.builder()
                    .userId(request.userId())
                    .unreadMessageCount(1L)
                    .updateDate(Instant.now())
                    .build();
        } else {
            entityToSave = counterEntity.get();
            entityToSave.setUnreadMessageCount(entityToSave.getUnreadMessageCount() + 1);
            entityToSave.setUpdateDate(Instant.now());
        }
        return mapper.entityToDto(repository.save(entityToSave));
    }

    @Cacheable(value = "counterCache", key = "@userServiceImpl.extractCurrentUserId()")
    @Override
    public Long getCount() {
        Optional<CounterEntity> entity = repository.findByUserId(userService.extractCurrentUserId());
        return entity.isPresent() ? entity.get().getUnreadMessageCount() : 0;
    }

    @Override
    public CounterDto decrement(DecrementCountRequest request) {
        Optional<CounterEntity> counterEntity = repository.findByUserId(request.userId());
        CounterEntity entityToSave;
        if (counterEntity.isEmpty()) {
            entityToSave = CounterEntity.builder()
                    .userId(request.userId())
                    .unreadMessageCount(0L)
                    .updateDate(Instant.now())
                    .build();
        } else {
            entityToSave = counterEntity.get();
            entityToSave.setUnreadMessageCount(entityToSave.getUnreadMessageCount() > request.countToDecrement() ?
                    entityToSave.getUnreadMessageCount() - request.countToDecrement() : 0L);
            entityToSave.setUpdateDate(Instant.now());
        }
        return mapper.entityToDto(repository.save(entityToSave));
    }
}
