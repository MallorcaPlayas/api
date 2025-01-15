package org.example.apirest.service.route;

import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;

import java.util.List;

public interface RouteService {
    List<RouteDto> findAll();
    RouteDto findOne(Long id);
    RouteDto save(CreateRouteDto route);
    RouteDto update(Long id, CreateRouteDto route);
    void delete(Long id);
}
