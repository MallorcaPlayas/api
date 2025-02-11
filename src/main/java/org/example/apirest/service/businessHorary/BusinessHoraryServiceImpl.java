package org.example.apirest.service.businessHorary;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.businessHorary.BusinessHoraryDto;
import org.example.apirest.dto.businessHorary.CreateBusinessHoraryDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BusinessHorary;
import org.example.apirest.repository.BusinessHoraryRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessHoraryServiceImpl implements DtoConverter<BusinessHorary, BusinessHoraryDto, CreateBusinessHoraryDto> {

    private final BusinessHoraryRepository repository;
    private final ModelMapper mapper;

    public List<BusinessHoraryDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public BusinessHoraryDto findOne(Long id) {
        BusinessHorary entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessHorary.class, id));
        return this.toDto(entity);
    }

    public BusinessHoraryDto save(CreateBusinessHoraryDto dto) {
        BusinessHorary entityToInsert = fromDto(dto);
        return toDto(repository.save(entityToInsert));
    }

    public BusinessHoraryDto update(Long id, CreateBusinessHoraryDto dto) {
        BusinessHorary oldEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessHorary.class, id));
        BusinessHorary newEntity = fromDto(dto);

        UtilsClass.updateFields(oldEntity, newEntity);
        return toDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        BusinessHorary entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessHorary.class, id));
        repository.delete(entity);
    }

    @Override
    public BusinessHoraryDto toDto(BusinessHorary entity) {
        return mapper.map(entity, BusinessHoraryDto.class);
    }

    @Override
    public List<BusinessHoraryDto> toDtoList(List<BusinessHorary> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public BusinessHorary fromDto(CreateBusinessHoraryDto dto) {
        return mapper.map(dto, BusinessHorary.class);
    }

    @Override
    public List<BusinessHorary> fromDtoList(List<CreateBusinessHoraryDto> dtos) {
        return dtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
