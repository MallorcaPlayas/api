package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.notification.NotificationDto;
import org.example.apirest.dto.notification.CreateNotificationDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.notification.NotificationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController extends GeneralizedController<NotificationDto, CreateNotificationDto> {
    public NotificationController(NotificationServiceImpl service) {
        super(service);
    }
}
