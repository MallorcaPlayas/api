package org.example.apirest.service.business;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Business;
import org.example.apirest.repository.BusinessRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements DtoConverter<Business,BusinessDto, CreateBusinessDto> {

    private final BusinessRepository repository;
    private final ModelMapper mapper;

    public List<BusinessDto> findAll() {
        return toDtoList(repository.findAll());
    }

    public BusinessDto findOne(Long id) {
        Business entity = repository.findById(id).orElseThrow(()-> new NotFoundException(Business.class,id));
        return this.toDto(entity);
    }

    public BusinessDto save(CreateBusinessDto entity) {
        Business entityToInsert = this.fromDto(entity);
        return this.toDto(repository.save(entityToInsert));
    }

    public BusinessDto update(Long id, CreateBusinessDto createEntity) {
        Business oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(Business.class, id));
        Business entityToInsert = this.fromDto(createEntity);

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return this.toDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        Business entity = repository.findById(id).orElseThrow(()-> new NotFoundException(Business.class,id));
        repository.delete(entity);
    }

    @Override
    public BusinessDto toDto(Business business) {
        return mapper.map(business,BusinessDto.class);
    }

    @Override
    public List<BusinessDto> toDtoList(List<Business> businesses) {
        return businesses.stream().map(this::toDto).toList();
    }

    @Override
    public Business fromDto(CreateBusinessDto createBusinessDto) {
        return mapper.map(createBusinessDto,Business.class);
    }

    @Override
    public List<Business> fromDtoList(List<CreateBusinessDto> createBusinessDtos) {
        return createBusinessDtos.stream().map(this::fromDto).toList();
    }
}
