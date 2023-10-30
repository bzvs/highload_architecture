package ru.bzvs.higharc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.dto.TextDto;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody TextDto dto) {
        template.convertAndSend("/topic/" + "2000001", dto);
        return ResponseEntity.ok().build();
    }
}
