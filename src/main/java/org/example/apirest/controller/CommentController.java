package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.comment.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController extends GeneralizedController<CommentDto, CreateCommentDto> {
    public CommentController(CommentServiceImpl service) {
        super(service);
    }
}
