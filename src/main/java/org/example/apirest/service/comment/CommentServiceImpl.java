package org.example.apirest.service.comment;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Camera;
import org.example.apirest.model.Comment;
import org.example.apirest.repository.CameraRepository;
import org.example.apirest.repository.CommentRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends GeneralizedServiceImpl<Comment, CommentDto, CreateCommentDto, CommentRepository> {
    public CommentServiceImpl(CommentRepository repository, DtoConverterImpl<Comment,CommentDto,CreateCommentDto> dtoConverter) {
        super(repository, dtoConverter, Comment.class, CommentDto.class);
    }
}
