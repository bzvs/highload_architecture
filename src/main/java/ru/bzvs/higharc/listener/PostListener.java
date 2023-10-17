package ru.bzvs.higharc.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.bzvs.higharc.service.WallService;

@Component
@RequiredArgsConstructor
public class PostListener {

    private static final String INVALIDATE_CACHE_QUEUE = "invalidateCacheQueue";
    private static final String FORM_CACHE_QUEUE_NAME = "formCacheQueue";

    private final WallService wallService;

    @RabbitListener(queues = INVALIDATE_CACHE_QUEUE)
    public void listenForInvalidate(Long authorId) {
        wallService.invalidateCache(authorId);
    }

    @RabbitListener(queues = FORM_CACHE_QUEUE_NAME)
    public void listenForForm(Long authorId) {
        wallService.feed(authorId);
    }
}
