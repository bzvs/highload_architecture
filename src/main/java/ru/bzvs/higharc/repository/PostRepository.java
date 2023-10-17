package ru.bzvs.higharc.repository;

import ru.bzvs.higharc.entity.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Long save(PostEntity entity);

    PostEntity update(PostEntity entity);

    void delete(Long id);

    Optional<PostEntity> findById(Long id);

    List<PostEntity> getPostsByUserId(Long userId, int offset, int limit);
}
