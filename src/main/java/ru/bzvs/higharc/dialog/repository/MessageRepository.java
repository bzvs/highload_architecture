package ru.bzvs.higharc.dialog.repository;

import org.springframework.data.tarantool.repository.Query;
import org.springframework.data.tarantool.repository.TarantoolRepository;
import ru.bzvs.higharc.dialog.entity.MessageEntity;

import java.util.List;

public interface MessageRepository extends TarantoolRepository<MessageEntity, Long> {

    @Query(function = "create_message")
    Integer create(Long from_id, Long to_id, String message_text);

//    Long create(MessageEntity entity);
//
    @Query(function = "find_by_from_id_and_to_id")
    List<MessageEntity> findByToAndFrom(Long from_id, Long to_id);
}
