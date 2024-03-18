package ru.bzvs.higharc.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.dto.CreateMessageRequest;
import ru.bzvs.higharc.dto.MessageDto;
import ru.bzvs.higharc.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("dialog/v2")
public class DialogController {

    private final MessageService service;

    private final Counter counter;

    public DialogController(MessageService service, MeterRegistry registry) {
        this.service = service;
        this.counter = Counter.builder("message_count").register(registry);
    }

    @PostMapping("{user_id}/send")
    public ResponseEntity<Void> send(@PathVariable(name = "user_id") Long userId,
                                     @RequestBody CreateMessageRequest messageRequest) {
        service.createMessage(userId, messageRequest);
        counter.increment();
        return ResponseEntity.ok().build();
    }

    @GetMapping("{user_id}/list")
    public ResponseEntity<List<MessageDto>> find(@PathVariable(name = "user_id") Long userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }
}
