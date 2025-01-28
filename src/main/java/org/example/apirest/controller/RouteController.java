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
    public RouteController(RouteServiceImpl service) {
        super(service);
    }


    @PostMapping("upload")
    public ResponseEntity<RouteDto> upload(@RequestPart CreateRouteDto entity , @RequestPart MultipartFile gpxFile) throws ParserConfigurationException, SAXException, IOException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RouteHandler gpxHandler = new RouteHandler();

        saxParser.parse(gpxFile.getInputStream(), gpxHandler);

        List<CreateLocationDto> result = gpxHandler.getLocations();

        entity.setLocations(result);
        RouteDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }
}
