package ru.bzvs.higharc.repository;

import ru.bzvs.higharc.entity.MessageEntity;

import java.util.List;

public interface MessageRepository {

    Long create(MessageEntity entity);

    List<MessageEntity> findByToAndFrom(Long from_id, Long to_id);
}
