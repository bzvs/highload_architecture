package ru.bzvs.higharc.controller;

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
@RequiredArgsConstructor
@RequestMapping("dialog/v2")
public class DialogController {

    private final MessageService service;

    @PostMapping("{user_id}/send")
    public ResponseEntity<Void> send(@PathVariable(name = "user_id") Long userId,
                                     @RequestBody CreateMessageRequest messageRequest) {
        service.createMessage(userId, messageRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{user_id}/list")
    public ResponseEntity<List<MessageDto>> find(@PathVariable(name = "user_id") Long userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }
}
