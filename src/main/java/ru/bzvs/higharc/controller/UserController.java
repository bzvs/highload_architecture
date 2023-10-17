package ru.bzvs.higharc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.dto.UserDto;
import ru.bzvs.higharc.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService service;

    @PostMapping("register")
    public ResponseEntity<Long> register(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<UserDto> find(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @GetMapping("search")
    public ResponseEntity<List<UserDto>> find(@RequestParam("first_name") String firstName,
                                              @RequestParam("last_name") String lastName) {
        return ResponseEntity.ok(service.findByFirstAndLastNames(firstName, lastName));
    }
}
