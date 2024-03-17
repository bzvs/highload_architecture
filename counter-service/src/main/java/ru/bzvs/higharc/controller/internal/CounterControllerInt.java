package ru.bzvs.higharc.controller.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.dto.CounterDto;
import ru.bzvs.higharc.dto.DecrementCountRequest;
import ru.bzvs.higharc.dto.IncrementCountRequest;
import ru.bzvs.higharc.service.CounterService;

@RestController
@RequiredArgsConstructor
@RequestMapping("int/counter")
public class CounterControllerInt {

    private final CounterService service;

    @PostMapping("increment")
    public ResponseEntity<CounterDto> increment(@RequestBody IncrementCountRequest request) {
        return ResponseEntity.ok(service.increment(request));
    }

    @PostMapping("decrement")
    public ResponseEntity<CounterDto> decrement(@RequestBody DecrementCountRequest request) {
        return ResponseEntity.ok(service.decrement(request));
    }
}
