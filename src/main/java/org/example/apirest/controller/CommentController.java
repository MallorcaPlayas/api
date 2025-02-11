package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.service.comment.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl service;

    @GetMapping
    public ResponseEntity<List<CommentDto>> index() {
        List<CommentDto> comments = service.findAll();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> show(@PathVariable Long id) {
        CommentDto comment = service.findOne(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CreateCommentDto entity) {
        CommentDto newComment = service.save(entity);
        return ResponseEntity.ok(newComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@RequestBody CreateCommentDto entity, @PathVariable Long id) {
        CommentDto updatedComment = service.update(id, entity);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
