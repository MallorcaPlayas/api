package org.example.apirest.service.comment;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.model.Comment;
import org.example.apirest.repository.CommentRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends GeneralizedServiceImpl<Comment, CommentDto, CreateCommentDto, CommentRepository> {
    public CommentServiceImpl(CommentRepository repository, DtoConverterGeneralizedImpl<Comment,CommentDto,CreateCommentDto> dtoConverter) {
        super(repository, dtoConverter, Comment.class, CommentDto.class);
    }
}
