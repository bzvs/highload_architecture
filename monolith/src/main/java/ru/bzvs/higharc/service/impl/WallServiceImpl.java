package ru.bzvs.higharc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.bzvs.higharc.dto.PostDto;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.mapper.PostEntityMapper;
import ru.bzvs.higharc.repository.PostRepository;
import ru.bzvs.higharc.service.UserService;
import ru.bzvs.higharc.service.WallService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WallServiceImpl implements WallService {

    private final CacheManager cacheManager;
    private final PostEntityMapper postMapper;
    private final PostRepository postRepository;
    private final UserService userService;

    private static final String WALL_CACHE = "wall";

    @Override
    @Cacheable(cacheNames = WALL_CACHE, key = "@userServiceImpl.extractCurrentUserId()")
    public List<PostDto> feed(int offset, int limit) {
        return postMapper.mapToDtos(postRepository.getPostsByUserId(userService.extractCurrentUserId(), offset, limit));
    }

    @Override
    @Cacheable(cacheNames = WALL_CACHE, key = "#authorId")
    public List<PostDto> feed(Long authorId) {
        return postMapper.mapToDtos(postRepository.getPostsByUserId(authorId, 0, 1000));
    }

    @Override
    public void invalidateCache(Long authorId) {
        List<UserDto> subscribers = userService.findSubscribers(authorId);
        subscribers.forEach(friend -> cacheManager.getCache("wall").evictIfPresent(friend.getId()));
    }

}
