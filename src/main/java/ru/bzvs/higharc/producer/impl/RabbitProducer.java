package ru.bzvs.higharc.producer.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.bzvs.higharc.producer.Producer;

@Component
@RequiredArgsConstructor
public class RabbitProducer implements Producer {

    private static final String INVALIDATE_CACHE_QUEUE = "invalidateCacheQueue";
    private static final String FORM_CACHE_QUEUE_NAME = "formCacheQueue";

    private final RabbitTemplate template;

    @Override
    public void produceForInvalidate(Long authorId) {
        template.convertAndSend(INVALIDATE_CACHE_QUEUE, authorId);
    }

    @Override
    public void produceForForming(Long authorId) {
        template.convertAndSend(FORM_CACHE_QUEUE_NAME, authorId);
    }
}
