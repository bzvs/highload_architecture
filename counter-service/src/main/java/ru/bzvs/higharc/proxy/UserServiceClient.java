package ru.bzvs.higharc.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.bzvs.higharc.dto.UserDto;

@FeignClient(name = "user-client", url = "${api.external.userBaseUrl}")
public interface UserServiceClient {

    @GetMapping("/int/user/get/{id}")
    UserDto find(@PathVariable Long id);
}
