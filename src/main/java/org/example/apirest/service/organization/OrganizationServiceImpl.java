package org.example.apirest.service.organization;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.model.Organization;
import org.example.apirest.repository.OrganizationRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl extends GeneralizedServiceImpl<Organization, OrganizationDto, CreateOrganizationDto, OrganizationRepository> {
    public OrganizationServiceImpl(OrganizationRepository repository, DtoConverterGeneralizedImpl<Organization,OrganizationDto,CreateOrganizationDto> dtoConverter) {
        super(repository, dtoConverter, Organization.class, OrganizationDto.class);
    }
}
