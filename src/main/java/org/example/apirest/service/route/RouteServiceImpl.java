package org.example.apirest.service.route;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Location;
import org.example.apirest.model.Photo;
import org.example.apirest.model.route.Route;
import org.example.apirest.model.route.RouteFireStore;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.repository.RouteRepository;
import org.example.apirest.repository.RouteRepositoryFirestore;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.RouteHandler;
import org.example.apirest.utils.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl {

    private final SAXParser saxParser;
    private final DtoConverterGeneralizedImpl<Location, LocationDto,CreateLocationDto> dtoConverterLocation;
    private final DtoConverter<Photo, PhotoDto> photoDtoConverter;
    private final DtoConverterGeneralizedImpl<Route,RouteDto,CreateRouteDto> dtoConverter;
    private final JpaRepository<Route,Long> repository;
    private final RouteRepositoryFirestore repositoryFirestore;

    public RouteFireStore findOneFireStore(Long id){
        return  repositoryFirestore.findById(id);
    }

    public List<RouteFireStore> findAllFireStore(){
        return repositoryFirestore.findAll();
    }

    public RouteFireStore saveFireStore(RouteFireStore routeFireStore){
        return repositoryFirestore.save(routeFireStore);
    }

    public void deleteFireStore(Long id){
        RouteFireStore routeFireStore = findOneFireStore(id);
        repositoryFirestore.delete(routeFireStore);
    }

    public RouteDto findOne(Long id){

        Route route = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class , id));

        RouteDto routeDto = dtoConverter.convertDto(route,RouteDto.class);

        List<PhotoDto> photoDtos = photoDtoConverter.entityListToDtoList(route.getPhotos());

        routeDto.setPhotos(photoDtos);

        return routeDto;
    }

    public List<RouteDto> findAll(){

        List<Route> routes = repository.findAll();

        return routes.stream()
                .map(route -> {
                    RouteDto routeDto = dtoConverter.convertDto(route,RouteDto.class);

                    List<PhotoDto> photoDtos = photoDtoConverter.entityListToDtoList(route.getPhotos());

                    routeDto.setPhotos(photoDtos);

                    return routeDto;
                    
                })
                .toList();
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

    public RouteDto update(Long id, CreateRouteDto createEntity) {
        Route oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class, id));
        Route entityToInsert = dtoConverter.convertToEntityFromCreateDto(createEntity, Route.class);

        if (oldEntity == null) {
            return null;
        }

        Utils.updateFields(oldEntity, entityToInsert);

        return dtoConverter.convertDto(repository.save(oldEntity), RouteDto.class);
    }

    public void delete(Long id) {
        Route entity = repository.findById(id).orElseThrow(()-> new NotFoundException(Route.class,id));
        repository.delete(entity);
    }
}
