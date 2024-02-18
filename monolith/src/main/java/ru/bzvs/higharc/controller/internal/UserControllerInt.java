package ru.bzvs.higharc.controller.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("int/user")
public class UserControllerInt {

    private final UserService service;

    @GetMapping("get/{id}")
    public ResponseEntity<UserDto> find(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }
}
