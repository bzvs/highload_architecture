package ru.bzvs.higharc.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.producer.Producer;
import ru.bzvs.higharc.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WallCacheScheduler {

    private final UserService userService;

    private final Producer producer;

    @Scheduled(fixedDelayString = "${scheduler.fixedDelay}")
    public void process() {
        List<UserDto> users = userService.findUsersWithFriendsAndPosts();
        users.forEach(user -> producer.produceForForming(user.getId()));
    }
}
