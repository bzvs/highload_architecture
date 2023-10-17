package ru.bzvs.higharc.service;

import ru.bzvs.higharc.dto.PostDto;
import ru.bzvs.higharc.dto.PostRequest;

import java.util.List;

public interface PostService {

    Long create(PostRequest request);

    void update(PostRequest request);

    void delete(Long id);

    PostDto find(Long id);
}
