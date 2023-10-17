package ru.bzvs.higharc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.dto.PostDto;
import ru.bzvs.higharc.dto.PostRequest;
import ru.bzvs.higharc.service.PostService;
import ru.bzvs.higharc.service.WallService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService service;

    private final WallService wallService;

    @PostMapping("create")
    public ResponseEntity<Long> create(@RequestBody PostRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody PostRequest request) {
        service.update(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<PostDto> find(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @GetMapping("feed")
    public ResponseEntity<List<PostDto>> feed(@RequestParam int offset, @RequestParam int limit) {
        return ResponseEntity.ok(wallService.feed(offset, limit));
    }
}
