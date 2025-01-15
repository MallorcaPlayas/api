package org.example.apirest.service.comment;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Comment;
import org.example.apirest.repository.CommentRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final DtoConverterImpl<Comment, CommentDto, CreateCommentDto> dtoConverter;

    @Override
    public List<CommentDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), CommentDto.class);
    }

    @Override
    public CommentDto findOne(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new NotFoundException(Comment.class, id));
        return dtoConverter.convertDto(comment, CommentDto.class);
    }

    @Override
    public CommentDto save(CreateCommentDto comment) {
        Comment commentToInsert = dtoConverter.convertToEntityFromCreateDto(comment, Comment.class);
        return dtoConverter.convertDto(repository.save(commentToInsert), CommentDto.class);
    }

    @Override
    public CommentDto update(Long id, CreateCommentDto comment) {
        Comment oldComment = repository.findById(id).orElseThrow(() -> new NotFoundException(Comment.class, id));
        Comment commentToInsert = dtoConverter.convertToEntityFromCreateDto(comment, Comment.class);

        if (oldComment == null) {
            return null;
        }

        UtilsClass.updateFields(oldComment, commentToInsert);

        return dtoConverter.convertDto(repository.save(oldComment), CommentDto.class);
    }

    @Override
    public void delete(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new NotFoundException(Comment.class, id));
        repository.delete(comment);
    }
}
