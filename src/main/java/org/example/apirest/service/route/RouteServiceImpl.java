package org.example.apirest.service.route;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Role;
import org.example.apirest.model.Route;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.repository.RouteRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl extends GeneralizedServiceImpl<Route, RouteDto, CreateRouteDto, RouteRepository> {
    public RouteServiceImpl(RouteRepository repository, DtoConverterImpl<Route,RouteDto,CreateRouteDto> dtoConverter) {
        super(repository, dtoConverter, Route.class, RouteDto.class);
    }
}
