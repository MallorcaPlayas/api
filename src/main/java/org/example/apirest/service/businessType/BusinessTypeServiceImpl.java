package org.example.apirest.service.businessType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BusinessType;
import org.example.apirest.repository.BusinessTypeRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessTypeServiceImpl implements DtoConverter<BusinessType, BusinessTypeDto, CreateBusinessTypeDto> {

    private final BusinessTypeRepository repository;
    private final ModelMapper mapper;

    public List<BusinessTypeDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public BusinessTypeDto findOne(Long id) {
        BusinessType businessType = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessType.class, id));
        return this.toDto(businessType);
    }

    public BusinessTypeDto save(CreateBusinessTypeDto createBusinessTypeDto) {
        BusinessType businessType = fromDto(createBusinessTypeDto);
        BusinessType savedBusinessType = repository.save(businessType);
        return toDto(savedBusinessType);
    }

    public BusinessTypeDto update(Long id, CreateBusinessTypeDto createBusinessTypeDto) {
        BusinessType oldBusinessType = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessType.class, id));
        BusinessType businessTypeToUpdate = fromDto(createBusinessTypeDto);

        UtilsClass.updateFields(oldBusinessType, businessTypeToUpdate);

        BusinessType savedBusinessType = repository.save(oldBusinessType);
        return toDto(savedBusinessType);
    }

    public void delete(Long id) {
        BusinessType businessType = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessType.class, id));
        repository.delete(businessType);
    }

    @Override
    public BusinessTypeDto toDto(BusinessType businessType) {
        return mapper.map(businessType, BusinessTypeDto.class);
    }

    @Override
    public List<BusinessTypeDto> toDtoList(List<BusinessType> businessTypes) {
        return businessTypes.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public BusinessType fromDto(CreateBusinessTypeDto createBusinessTypeDto) {
        return mapper.map(createBusinessTypeDto, BusinessType.class);
    }

    @Override
    public List<BusinessType> fromDtoList(List<CreateBusinessTypeDto> createBusinessTypeDtos) {
        return createBusinessTypeDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
