package ru.bzvs.higharc.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private static final String FORM_CACHE_QUEUE_NAME = "formCacheQueue";

    @Bean
    public Queue formCacheQueue() {
        return new Queue(FORM_CACHE_QUEUE_NAME, false);
    }
}
