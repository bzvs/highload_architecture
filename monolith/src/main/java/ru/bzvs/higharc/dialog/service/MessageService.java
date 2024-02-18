package ru.bzvs.higharc.dialog.service;

import ru.bzvs.higharc.dialog.dto.CreateMessageRequest;
import ru.bzvs.higharc.dialog.dto.MessageDto;

import java.util.List;

public interface MessageService {
    void createMessage(Long userId, CreateMessageRequest messageRequest);

    List<MessageDto> findByUserId(Long userId);
}
