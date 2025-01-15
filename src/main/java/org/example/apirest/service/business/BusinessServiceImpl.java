package org.example.apirest.service.business;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Business;
import org.example.apirest.repository.BusinessRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository repository;
    private final DtoConverterImpl<Business, BusinessDto, CreateBusinessDto> dtoConverter;

    @Override
    public List<BusinessDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), BusinessDto.class);
    }

    @Override
    public BusinessDto findOne(Long id) {
        Business business = repository.findById(id).orElseThrow(() -> new NotFoundException(Business.class, id));
        return dtoConverter.convertDto(business, BusinessDto.class);
    }

    @Override
    public BusinessDto save(CreateBusinessDto business) {
        Business businessToInsert = dtoConverter.convertToEntityFromCreateDto(business, Business.class);
        return dtoConverter.convertDto(repository.save(businessToInsert), BusinessDto.class);
    }

    @Override
    public BusinessDto update(Long id, CreateBusinessDto business) {
        Business oldBusiness = repository.findById(id).orElseThrow(() -> new NotFoundException(Business.class, id));
        Business businessToInsert = dtoConverter.convertToEntityFromCreateDto(business, Business.class);

        if (oldBusiness == null) {
            return null;
        }

        UtilsClass.updateFields(oldBusiness, businessToInsert);

        return dtoConverter.convertDto(repository.save(oldBusiness), BusinessDto.class);
    }

    @Override
    public void delete(Long id) {
        Business business = repository.findById(id).orElseThrow(() -> new NotFoundException(Business.class, id));
        repository.delete(business);
    }
}
