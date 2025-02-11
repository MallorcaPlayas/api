package org.example.apirest.service.comment;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Comment;
import org.example.apirest.repository.CommentRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements DtoConverter<Comment, CommentDto, CreateCommentDto> {

    private final CommentRepository repository;
    private final ModelMapper mapper;

    public List<CommentDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public CommentDto findOne(Long id) {
        Comment comment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Comment.class, id));
        return this.toDto(comment);
    }

    public CommentDto save(CreateCommentDto entity) {
        Comment comment = fromDto(entity);
        return toDto(repository.save(comment));
    }

    public CommentDto update(Long id, CreateCommentDto createEntity) {
        Comment oldEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Comment.class, id));
        Comment entityToInsert = this.fromDto(createEntity);

        // Actualiza los campos modificables de la entidad existente.
        UtilsClass.updateFields(oldEntity, entityToInsert);

        return this.toDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        Comment entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Comment.class, id));
        repository.delete(entity);
    }

    @Override
    public CommentDto toDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> toDtoList(List<Comment> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Comment fromDto(CreateCommentDto createCommentDto) {
        return mapper.map(createCommentDto, Comment.class);
    }

    @Override
    public List<Comment> fromDtoList(List<CreateCommentDto> createCommentDtos) {
        return createCommentDtos.stream().map(this::fromDto).toList();
    }
}
