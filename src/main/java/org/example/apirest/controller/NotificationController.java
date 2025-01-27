package org.example.apirest.controller;

import org.example.apirest.dto.notification.NotificationDto;
import org.example.apirest.dto.notification.CreateNotificationDto;
import org.example.apirest.service.notification.NotificationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController extends GeneralizedControllerImpl<NotificationDto, CreateNotificationDto> {
    public NotificationController(NotificationServiceImpl service) {
        super(service);
    }
}
