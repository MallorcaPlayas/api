package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.service.route.RouteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/routes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RouteController {

    private final RouteServiceImpl routeService;

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

    @PostMapping(consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('createRoute')")
    public ResponseEntity<RouteDto> create(@ModelAttribute CreateRouteDto entity) {
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

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('uploadRoute')")
    public ResponseEntity<RouteDto> upload(@RequestPart(required = false) CreateRouteDto route,
                                           @RequestPart MultipartFile file) {
        RouteDto routeDto = null;

        if(route == null){
            routeDto = routeService.upload(file);
        }else {
            routeDto = routeService.upload(file,route);
        }

        return ResponseEntity.ok(routeDto);
    }
}
