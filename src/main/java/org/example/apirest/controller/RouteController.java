package org.example.apirest.controller;

import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.service.route.RouteServiceImpl;
import org.example.apirest.utils.RouteHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController extends GeneralizedController<RouteDto, CreateRouteDto> {
    private final RouteServiceImpl routeService;
    public RouteController(RouteServiceImpl service, RouteServiceImpl routeService) {
        super(service);
        this.routeService = routeService;
    }


    @PostMapping("upload")
    public ResponseEntity<RouteDto> upload(@RequestPart MultipartFile gpxFile) throws ParserConfigurationException, SAXException, IOException, ParserConfigurationException, IOException {
        RouteDto newEntity = routeService.alex(gpxFile);
        return ResponseEntity.ok(newEntity);
    }
}
