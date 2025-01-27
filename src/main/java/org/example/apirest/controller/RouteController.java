package org.example.apirest.controller;

import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.service.route.RouteServiceImpl;
import org.example.apirest.utils.LocationHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController extends GeneralizedControllerImpl<RouteDto, CreateRouteDto> {
    public RouteController(RouteServiceImpl service) {
        super(service);
    }


    @PostMapping
    public ResponseEntity<RouteDto> create(@RequestBody CreateRouteDto entity , @RequestPart("gpx") MultipartFile gpxFile) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        LocationHandler gpxHandler = new LocationHandler();

        saxParser.parse((InputStream) gpxFile, gpxHandler);

        List<LocationDto> result = gpxHandler.getLocations();

        entity.setLocations(result);
        RouteDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }
}
