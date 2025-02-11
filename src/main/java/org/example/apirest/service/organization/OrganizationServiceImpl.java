package org.example.apirest.service.organization;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Organization;
import org.example.apirest.repository.OrganizationRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements DtoConverter<Organization, OrganizationDto, CreateOrganizationDto> {

    private final OrganizationRepository repository;
    private final ModelMapper mapper;

    public List<OrganizationDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public OrganizationDto findOne(Long id) {
        Organization organization = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Organization.class, id));
        return this.toDto(organization);
    }

    public OrganizationDto save(CreateOrganizationDto createOrganizationDto) {
        Organization organization = fromDto(createOrganizationDto);
        Organization savedOrganization = repository.save(organization);
        return toDto(savedOrganization);
    }

    public OrganizationDto update(Long id, CreateOrganizationDto createOrganizationDto) {
        Organization oldOrganization = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Organization.class, id));
        Organization organizationToUpdate = fromDto(createOrganizationDto);

        UtilsClass.updateFields(oldOrganization, organizationToUpdate);

        Organization savedOrganization = repository.save(oldOrganization);
        return toDto(savedOrganization);
    }

    public void delete(Long id) {
        Organization organization = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Organization.class, id));
        repository.delete(organization);
    }

    @Override
    public OrganizationDto toDto(Organization organization) {
        return mapper.map(organization, OrganizationDto.class);
    }

    @Override
    public List<OrganizationDto> toDtoList(List<Organization> organizations) {
        return organizations.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Organization fromDto(CreateOrganizationDto createOrganizationDto) {
        return mapper.map(createOrganizationDto, Organization.class);
    }

    @Override
    public List<Organization> fromDtoList(List<CreateOrganizationDto> createOrganizationDtos) {
        return createOrganizationDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
