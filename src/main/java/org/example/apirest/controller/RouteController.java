package org.example.apirest.controller;

import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.service.route.RouteServiceImpl;
import org.example.apirest.utils.RouteHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/routes")
@CrossOrigin(origins = "*")
public class RouteController {
    private final RouteServiceImpl routeService;

    protected final GeneralizedService<Dto,CreateDto> service;

    @GetMapping
    public ResponseEntity<List<Dto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Dto> create(@RequestBody CreateDto entity) {
        Dto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dto> update(@RequestBody CreateDto entity, @PathVariable Long id) {
        Dto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('readRoute')")
    public ResponseEntity<List<RouteDto>> index() {
        return ResponseEntity.ok(routeService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('readRoute')")
    public ResponseEntity<RouteDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('createRoute')")
    public ResponseEntity<RouteDto> create(@RequestBody CreateRouteDto entity) {
        RouteDto newEntity = routeService.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('updateRoute')")
    public ResponseEntity<RouteDto> update(@RequestBody CreateRouteDto entity, @PathVariable Long id) {
        RouteDto updated = routeService.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('deleteRoute')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        routeService.delete(id);
    }

    @PostMapping("upload")
    @PreAuthorize("hasAuthority('uploadRoute')")
    public ResponseEntity<List<RouteDto>> upload(@RequestPart List<MultipartFile> gpxFiles) {
        List<RouteDto> routeDtos = routeService.uploadList(gpxFiles);
        return ResponseEntity.ok(routeDtos);
    }
}
