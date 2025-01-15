package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.service.comment.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService service;

    @GetMapping
    public ResponseEntity<List<CommentDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CreateCommentDto comment) {
        CommentDto newComment = service.save(comment);
        return ResponseEntity.created(URI.create("/api/comments/" + newComment.getId())).body(newComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@RequestBody CreateCommentDto comment, @PathVariable Long id) {
        CommentDto updatedComment = service.update(id, comment);
        return ResponseEntity.created(URI.create("/api/comments/" + id)).body(updatedComment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
