package ru.bzvs.higharc.dialog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bzvs.higharc.dialog.dto.CreateMessageRequest;
import ru.bzvs.higharc.dialog.dto.MessageDto;
import ru.bzvs.higharc.dialog.mapper.MessageEntityMapper;
import ru.bzvs.higharc.dialog.repository.MessageRepository;
import ru.bzvs.higharc.dialog.service.MessageService;
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
        repository.create(userService.extractCurrentUserId(), userId, messageRequest.text());
//        repository.(MessageEntity.builder()
//                .to(userId)
//                .from(userService.extractCurrentUserId())
//                .text(messageRequest.text())
//                .build());
    }

    @Override
    public List<MessageDto> findByUserId(Long userId) {
        return mapper.mapToDtos(repository.findByToAndFrom(userService.extractCurrentUserId(), userId));
    }
}
