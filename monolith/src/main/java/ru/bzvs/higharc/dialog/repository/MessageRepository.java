package ru.bzvs.higharc.dialog.repository;

import ru.bzvs.higharc.dialog.entity.MessageEntity;

import java.util.List;

public interface MessageRepository {

    Long create(MessageEntity entity);

    List<MessageEntity> findByToAndFrom(Long from_id, Long to_id);
}
