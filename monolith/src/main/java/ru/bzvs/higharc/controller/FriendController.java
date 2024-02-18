package ru.bzvs.higharc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("friend")
public class FriendController {

    private final UserService service;

    @PutMapping("set/{user_id}")
    public ResponseEntity<Void> addFriend(@PathVariable Long user_id) {
        service.addFriend(user_id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("delete/{user_id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long user_id) {
        service.deleteFriend(user_id);
        return ResponseEntity.ok().build();
    }
}
