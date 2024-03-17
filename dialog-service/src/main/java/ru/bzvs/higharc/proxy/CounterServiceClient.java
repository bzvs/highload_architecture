package ru.bzvs.higharc.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bzvs.higharc.dto.CounterDto;
import ru.bzvs.higharc.dto.DecrementCountRequest;
import ru.bzvs.higharc.dto.IncrementCountRequest;
import ru.bzvs.higharc.dto.UserDto;

@FeignClient(name = "counter-client", url = "${api.external.counterBaseUrl}")
public interface CounterServiceClient {

    @PostMapping ("/int/counter/increment")
    CounterDto increment(@RequestBody IncrementCountRequest request);

    @PostMapping ("/int/counter/decrement")
    CounterDto decrement(@RequestBody DecrementCountRequest request);
}
