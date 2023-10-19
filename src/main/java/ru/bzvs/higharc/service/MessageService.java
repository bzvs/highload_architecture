package ru.bzvs.higharc.service;

import ru.bzvs.higharc.dto.CreateMessageRequest;
import ru.bzvs.higharc.dto.MessageDto;

import java.util.List;

public interface MessageService {
    void createMessage(Long userId, CreateMessageRequest messageRequest);

    List<MessageDto> findByUserId(Long userId);
}
