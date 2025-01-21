package org.example.apirest.service.organization;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.notification.CreateNotificationDto;
import org.example.apirest.dto.notification.NotificationDto;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Notification;
import org.example.apirest.model.Organization;
import org.example.apirest.repository.NotificationRepository;
import org.example.apirest.repository.OrganizationRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl extends GeneralizedServiceImpl<Organization, OrganizationDto, CreateOrganizationDto, OrganizationRepository> {
    public OrganizationServiceImpl(OrganizationRepository repository, DtoConverterImpl<Organization,OrganizationDto,CreateOrganizationDto> dtoConverter) {
        super(repository, dtoConverter, Organization.class, OrganizationDto.class);
    }
}
