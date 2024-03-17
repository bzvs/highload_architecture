package ru.bzvs.higharc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bzvs.higharc.dto.CreateMessageRequest;
import ru.bzvs.higharc.dto.DecrementCountRequest;
import ru.bzvs.higharc.dto.IncrementCountRequest;
import ru.bzvs.higharc.dto.MessageDto;
import ru.bzvs.higharc.entity.MessageEntity;
import ru.bzvs.higharc.exception.CounterException;
import ru.bzvs.higharc.mapper.MessageEntityMapper;
import ru.bzvs.higharc.proxy.CounterServiceClient;
import ru.bzvs.higharc.repository.MessageRepository;
import ru.bzvs.higharc.service.MessageService;
import ru.bzvs.higharc.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    private final UserService userService;

    private final CounterServiceClient counterServiceClient;

    private final MessageEntityMapper mapper;

    @Override
    public void createMessage(Long userId, CreateMessageRequest messageRequest) {
        Long createdMessageId = repository.create(MessageEntity.builder()
                .to(userId)
                .from(userService.extractCurrentUserId())
                .text(messageRequest.text())
                .build());
        try {
            counterServiceClient.increment(new IncrementCountRequest(userId));
        } catch (Exception e) {
            repository.delete(createdMessageId);
            throw new CounterException();
        }
    }

    @Override
    public List<MessageDto> findByUserId(Long userId) {
        List<MessageDto> messages = mapper.mapToDtos(repository.findByToAndFrom(userService.extractCurrentUserId(), userId));
        try {
            counterServiceClient.decrement(new DecrementCountRequest(userId, (long) messages.size()));
        } catch (Exception e) {
            throw new CounterException();
        }

        return messages;
    }
}
