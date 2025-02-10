package org.example.apirest.service.route;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Location;
import org.example.apirest.model.Route;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.repository.RouteRepository;
import org.example.apirest.utils.RouteHandler;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl{

    private final SAXParser saxParser;

    rotected final R repository;

    @Override
    public List<Dto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), dtoClass);
    }

    @Override
    public Dto findOne(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        return dtoConverter.convertDto(entity, dtoClass);
    }

    @Override
    public Dto save(CreateDto entity) {
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, entityClass);
        return dtoConverter.convertDto(repository.save(entityToInsert), dtoClass);
    }

    @Override
    public Dto update(Long id, CreateDto createEntity) {
        Entity oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(entityClass, id));
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(createEntity, entityClass);

        if (oldEntity == null) {
            return null;
        }

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return dtoConverter.convertDto(repository.save(oldEntity), dtoClass);
    }

    @Override
    public void delete(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        repository.delete(entity);
    }

    public RouteDto save(CreateRouteDto entity) {
        Route route = dtoConverter.convertToEntityFromCreateDto(entity, Route.class);
        List<Location> locations = dtoConverterLocation.convertToEntityListFromCreateDto(entity.getLocations(),Location.class);
        System.out.println(locations);
        for(Location location : locations){
           location.setRoute(route);
        }
        route.setLocations(locations);
        return dtoConverter.convertDto(repository.save(route),RouteDto.class);
    }

    @SneakyThrows
    public RouteDto upload(MultipartFile multipartFile){
        RouteHandler routeHandler = new RouteHandler();

        saxParser.parse(multipartFile.getInputStream(),routeHandler);
        CreateRouteDto createRouteDto = routeHandler.getRoute();

        Route route = dtoConverter.convertToEntityFromCreateDto(createRouteDto,Route.class);

        List<Location> locations = dtoConverterLocation.convertToEntityListFromCreateDto(createRouteDto.getLocations(),Location.class);

        for(Location location : locations){
            location.setRoute(route);
        }

        route.setLocations(locations);
        return dtoConverter.convertDto(repository.save(route),RouteDto.class);
    }

    @SneakyThrows
    public List<RouteDto> uploadList(List<MultipartFile> files) {
        return files.stream().map(this::upload).toList();
    }
}
