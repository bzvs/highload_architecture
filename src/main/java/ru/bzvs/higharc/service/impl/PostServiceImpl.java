package ru.bzvs.higharc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.bzvs.higharc.dto.PostDto;
import ru.bzvs.higharc.dto.PostRequest;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.entity.PostEntity;
import ru.bzvs.higharc.mapper.PostEntityMapper;
import ru.bzvs.higharc.producer.Producer;
import ru.bzvs.higharc.repository.PostRepository;
import ru.bzvs.higharc.service.PostService;
import ru.bzvs.higharc.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    private final UserService userService;

    private final PostEntityMapper mapper;

    private final Producer producer;

    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public Long create(PostRequest request) {
        PostEntity savedEntity = repository.save(mapper.dtoToEntity(buildFromRequest(request)));
        List<UserDto> subscribers = userService.findSubscribers(userService.extractCurrentUserId());
        if (!CollectionUtils.isEmpty(subscribers)) {
            subscribers.forEach(subscriber -> sendWallUpdateToSubscriber(subscriber, mapper.entityToDto(savedEntity)));
        }
        producer.produceForInvalidate(userService.extractCurrentUserId());
        return savedEntity.getId();

    }

    @Override
    public void update(PostRequest request) {
        repository.update(mapper.dtoToEntity(buildFromRequest(request)));
        producer.produceForInvalidate(userService.extractCurrentUserId());
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
        producer.produceForInvalidate(userService.extractCurrentUserId());
    }

    @Override
    public PostDto find(Long id) {
        return mapper.entityToDto(repository.findById(id).orElseThrow());
    }

    private PostDto buildFromRequest(PostRequest request) {
        return PostDto.builder()
                .id(request.id())
                .authorId(userService.extractCurrentUserId())
                .text(request.postText())
                .build();
    }

    private void sendWallUpdateToSubscriber(UserDto subscriber, PostDto postDto) {
        messagingTemplate.convertAndSend("/topic/" + subscriber.getId(), postDto);
    }
}
