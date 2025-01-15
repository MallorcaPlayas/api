package org.example.apirest.service.organization;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Organization;
import org.example.apirest.repository.OrganizationRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final DtoConverterImpl<Organization, OrganizationDto, CreateOrganizationDto> dtoConverter;

    @Override
    public List<OrganizationDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), OrganizationDto.class);
    }

    @Override
    public OrganizationDto findOne(Long id) {
        Organization organization = repository.findById(id).orElseThrow(() -> new NotFoundException(Organization.class, id));
        return dtoConverter.convertDto(organization, OrganizationDto.class);
    }

    @Override
    public OrganizationDto save(CreateOrganizationDto organization) {
        Organization organizationToInsert = dtoConverter.convertToEntityFromCreateDto(organization, Organization.class);
        return dtoConverter.convertDto(repository.save(organizationToInsert), OrganizationDto.class);
    }

    @Override
    public OrganizationDto update(Long id, CreateOrganizationDto organization) {
        Organization oldOrganization = repository.findById(id).orElseThrow(() -> new NotFoundException(Organization.class, id));
        Organization organizationToInsert = dtoConverter.convertToEntityFromCreateDto(organization, Organization.class);

        if (oldOrganization == null) {
            return null;
        }

        UtilsClass.updateFields(oldOrganization, organizationToInsert);

        return dtoConverter.convertDto(repository.save(oldOrganization), OrganizationDto.class);
    }

    @Override
    public void delete(Long id) {
        Organization organization = repository.findById(id).orElseThrow(() -> new NotFoundException(Organization.class, id));
        repository.delete(organization);
    }
}
