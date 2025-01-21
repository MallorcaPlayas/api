package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.notification.NotificationDto;
import org.example.apirest.dto.notification.CreateNotificationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService service;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<NotificationDto> create(@RequestBody CreateNotificationDto notification) {
        NotificationDto newNotification = service.save(notification);
        return ResponseEntity.created(URI.create("/api/notifications/" + newNotification.getId())).body(newNotification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> update(@RequestBody CreateNotificationDto notification, @PathVariable Long id) {
        NotificationDto updatedNotification = service.update(id, notification);
        return ResponseEntity.created(URI.create("/api/notifications/" + id)).body(updatedNotification);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
