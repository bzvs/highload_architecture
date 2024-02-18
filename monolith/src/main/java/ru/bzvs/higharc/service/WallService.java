package ru.bzvs.higharc.service;

import ru.bzvs.higharc.dto.PostDto;

import java.util.List;

public interface WallService {

    List<PostDto> feed(int offset, int limit);

    List<PostDto> feed(Long authorId);

    void invalidateCache(Long authorId);
}
