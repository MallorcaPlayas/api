package org.example.apirest.service.billType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BillType;
import org.example.apirest.repository.BillTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillTypeServiceImpl implements BillTypeService {

    private final BillTypeRepository repository;
    private final DtoConverterImpl<BillType, BillTypeDto, CreateBillTypeDto> dtoConverter;

    @Override
    public List<BillTypeDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), BillTypeDto.class);
    }

    @Override
    public BillTypeDto findOne(Long id) {
        BillType billType = repository.findById(id).orElseThrow(() -> new NotFoundException(BillType.class, id));
        return dtoConverter.convertDto(billType, BillTypeDto.class);
    }

    @Override
    public BillTypeDto save(CreateBillTypeDto billType) {
        BillType billTypeToInsert = dtoConverter.convertToEntityFromCreateDto(billType, BillType.class);
        return dtoConverter.convertDto(repository.save(billTypeToInsert), BillTypeDto.class);
    }

    @Override
    public BillTypeDto update(Long id, CreateBillTypeDto billType) {
        BillType oldBillType = repository.findById(id).orElseThrow(() -> new NotFoundException(BillType.class, id));
        BillType billTypeToInsert = dtoConverter.convertToEntityFromCreateDto(billType, BillType.class);

        if (oldBillType == null) {
            return null;
        }

        oldBillType.setName(billTypeToInsert.getName());

        return dtoConverter.convertDto(repository.save(oldBillType), BillTypeDto.class);
    }

    @Override
    public void delete(Long id) {
        BillType billType = repository.findById(id).orElseThrow(() -> new NotFoundException(BillType.class, id));
        repository.delete(billType);
    }
}
