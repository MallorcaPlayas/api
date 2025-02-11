package org.example.apirest.service.billType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BillType;
import org.example.apirest.repository.BillTypeRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillTypeServiceImpl implements DtoConverter<BillType, BillTypeDto, CreateBillTypeDto> {
    private final BillTypeRepository repository;
    
    public List<BillTypeDto> findAll() {
        return toDtoList(repository.findAll());
    }

    
    public BillTypeDto findOne(Long id) {
        BillType entity = repository.findById(id).orElseThrow(()-> new NotFoundException(BillType.class,id));
        return this.toDto(entity);
    }

    
    public BillTypeDto save(CreateBillTypeDto entity) {
        BillType entityToInsert = this.fromDto(entity);
        return this.toDto(repository.save(entityToInsert));
    }

    
    public BillTypeDto update(Long id, CreateBillTypeDto createEntity) {
        BillType oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(BillType.class, id));
        BillType entityToInsert = this.fromDto(createEntity);

        if (oldEntity == null) {
            return null;
        }

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return this.toDto(repository.save(oldEntity));
    }

    
    public void delete(Long id) {
        BillType entity = repository.findById(id).orElseThrow(()-> new NotFoundException(BillType.class,id));
        repository.delete(entity);
    }

    @Override
    public BillTypeDto toDto(BillType billType) {
        return null;
    }

    @Override
    public List<BillTypeDto> toDtoList(List<BillType> billTypes) {
        return List.of();
    }

    @Override
    public BillType fromDto(CreateBillTypeDto createBillTypeDto) {
        return null;
    }

    @Override
    public List<BillType> fromDtoList(List<CreateBillTypeDto> createBillTypeDtos) {
        return List.of();
    }
}
