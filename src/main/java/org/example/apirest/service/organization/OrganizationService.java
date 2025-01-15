package org.example.apirest.service.organization;

import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDto> findAll();
    OrganizationDto findOne(Long id);
    OrganizationDto save(CreateOrganizationDto organization);
    OrganizationDto update(Long id, CreateOrganizationDto organization);
    void delete(Long id);
}
