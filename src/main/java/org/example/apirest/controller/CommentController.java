package org.example.apirest.controller;

import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.service.comment.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController extends GeneralizedController<CommentDto, CreateCommentDto> {
    public CommentController(CommentServiceImpl service) {
        super(service);
    }
}
