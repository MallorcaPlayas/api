package org.example.apirest.service.route;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Location;
import org.example.apirest.model.Route;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.repository.RouteRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.service.location.LocationServiceImpl;
import org.example.apirest.utils.RouteHandler;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements DtoConverter<Route,RouteDto, CreateRouteDto> {

    private final SAXParser saxParser;
    private final RouteRepository repository;
    private final ModelMapper mapper;
    private final LocationServiceImpl locationService;

    public List<RouteDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public RouteDto findOne(Long id) {
        Route route = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Route.class, id));
        return this.toDto(route);
    }

    public RouteDto update(Long id, CreateRouteDto createRouteDto) {
        Route oldRoute = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Route.class, id));
        Route routeToUpdate = fromDto(createRouteDto);

        UtilsClass.updateFields(oldRoute, routeToUpdate);

        Route savedRoute = repository.save(oldRoute);
        return toDto(savedRoute);
    }

    public void delete(Long id) {
        Route route = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Route.class, id));
        repository.delete(route);
    }



    public RouteDto save(CreateRouteDto entity) {
        Route route = fromDto(entity);
        return this.toDto(repository.save(route));
    }

    @SneakyThrows
    public RouteDto upload(MultipartFile multipartFile){
        RouteHandler routeHandler = new RouteHandler();

        saxParser.parse(multipartFile.getInputStream(),routeHandler);
        Route route = routeHandler.getRoute();

        return toDto(repository.save(route));
    }

    @SneakyThrows
    public List<RouteDto> uploadList(List<MultipartFile> files) {
        return files.stream().map(this::upload).toList();
    }

    @Override
    public List<RouteDto> toDtoList(List<Route> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public RouteDto toDto(Route entity) {
        return mapper.map(entity, RouteDto.class);
    }

    @Override
    @SneakyThrows
    public Route fromDto(CreateRouteDto dto) {
        Route route = mapper.map(dto, Route.class);
        RouteHandler routeHandler = new RouteHandler();

        saxParser.parse(dto.getFile().getInputStream(),routeHandler);

        List<Location> locations = routeHandler.getRoute().getLocations();

        for(Location location : locations){
            location.setRoute(route);
        }

        route.setLocations(locations);
        return route;
    }

    @Override
    public List<Route> fromDtoList(List<CreateRouteDto> createDtos) {
        return createDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
