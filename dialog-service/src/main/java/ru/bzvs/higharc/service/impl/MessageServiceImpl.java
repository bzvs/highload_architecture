package ru.bzvs.higharc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bzvs.higharc.dto.CreateMessageRequest;
import ru.bzvs.higharc.dto.MessageDto;
import ru.bzvs.higharc.entity.MessageEntity;
import ru.bzvs.higharc.mapper.MessageEntityMapper;
import ru.bzvs.higharc.repository.MessageRepository;
import ru.bzvs.higharc.service.MessageService;
import ru.bzvs.higharc.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    private final UserService userService;

    private final MessageEntityMapper mapper;

    @Override
    public void createMessage(Long userId, CreateMessageRequest messageRequest) {
        repository.create(MessageEntity.builder()
                .to(userId)
                .from(userService.extractCurrentUserId())
                .text(messageRequest.text())
                .build());
    }

    @Override
    public List<MessageDto> findByUserId(Long userId) {
        return mapper.mapToDtos(repository.findByToAndFrom(userService.extractCurrentUserId(), userId));
    }
}
