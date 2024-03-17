package ru.bzvs.higharc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.dto.CounterDto;
import ru.bzvs.higharc.dto.IncrementCountRequest;
import ru.bzvs.higharc.service.CounterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("counter/v1")
public class CounterController {

    private final CounterService service;

    @GetMapping("count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(service.getCount());
    }
}
