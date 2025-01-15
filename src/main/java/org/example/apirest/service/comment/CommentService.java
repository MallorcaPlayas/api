package org.example.apirest.service.comment;

import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAll();
    CommentDto findOne(Long id);
    CommentDto save(CreateCommentDto comment);
    CommentDto update(Long id, CreateCommentDto comment);
    void delete(Long id);
}
