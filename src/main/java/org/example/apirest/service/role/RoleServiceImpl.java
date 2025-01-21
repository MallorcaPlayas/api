package org.example.apirest.service.role;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.PointOfInterestType;
import org.example.apirest.model.Role;
import org.example.apirest.repository.PointOfInterestTypeRepository;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends GeneralizedServiceImpl<Role, RoleDto, CreateRoleDto, RoleRepository> {
    public RoleServiceImpl(RoleRepository repository, DtoConverterImpl<Role,RoleDto,CreateRoleDto> dtoConverter) {
        super(repository, dtoConverter, Role.class, RoleDto.class);
    }
}
