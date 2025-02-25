package org.example.apirest.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BaseEntity;
import org.example.apirest.utils.Utils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
    public class  GeneralizedServiceImpl<Entity extends BaseEntity,Dto,CreateDto, R extends JpaRepository<Entity,Long>>
        implements GeneralizedService<Dto,CreateDto> {

    protected final R repository;
    protected final DtoConverterGeneralizedImpl<Entity, Dto, CreateDto> dtoConverter;
    protected final Class<Entity> entityClass;
    protected final Class<Dto> dtoClass;

    @Override
    public List<Dto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), dtoClass);
    }

    @Override
    public Dto findOne(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        return dtoConverter.convertDto(entity, dtoClass);
    }

    @Override
    public Dto save(CreateDto entity) {
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, entityClass);
        return dtoConverter.convertDto(repository.save(entityToInsert), dtoClass);
    }

    @Override
    public Dto update(Long id, CreateDto createEntity) {
        Entity oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(entityClass, id));
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(createEntity, entityClass);

        if (oldEntity == null) {
            return null;
        }

        Utils.updateFields(oldEntity, entityToInsert);

        return dtoConverter.convertDto(repository.save(oldEntity), dtoClass);
    }

    @Override
    public void delete(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        repository.delete(entity);
    }
}
