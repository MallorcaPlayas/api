package org.example.apirest.service.businessType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BusinessType;
import org.example.apirest.repository.BusinessTypeRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessTypeServiceImpl implements BusinessTypeService {

    private final BusinessTypeRepository repository;
    private final DtoConverterImpl<BusinessType, BusinessTypeDto, CreateBusinessTypeDto> dtoConverter;

    @Override
    public List<BusinessTypeDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), BusinessTypeDto.class);
    }

    @Override
    public BusinessTypeDto findOne(Long id) {
        BusinessType businessType = repository.findById(id).orElseThrow(() -> new NotFoundException(BusinessType.class, id));
        return dtoConverter.convertDto(businessType, BusinessTypeDto.class);
    }

    @Override
    public BusinessTypeDto save(CreateBusinessTypeDto businessType) {
        BusinessType businessTypeToInsert = dtoConverter.convertToEntityFromCreateDto(businessType, BusinessType.class);
        return dtoConverter.convertDto(repository.save(businessTypeToInsert), BusinessTypeDto.class);
    }

    @Override
    public BusinessTypeDto update(Long id, CreateBusinessTypeDto businessType) {
        BusinessType oldBusinessType = repository.findById(id).orElseThrow(() -> new NotFoundException(BusinessType.class, id));
        BusinessType businessTypeToInsert = dtoConverter.convertToEntityFromCreateDto(businessType, BusinessType.class);

        if (oldBusinessType == null) {
            return null;
        }

        UtilsClass.updateFields(oldBusinessType, businessTypeToInsert);

        return dtoConverter.convertDto(repository.save(oldBusinessType), BusinessTypeDto.class);
    }

    @Override
    public void delete(Long id) {
        BusinessType businessType = repository.findById(id).orElseThrow(() -> new NotFoundException(BusinessType.class, id));
        repository.delete(businessType);
    }
}
