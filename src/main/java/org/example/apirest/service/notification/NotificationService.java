package org.example.apirest.service.notification;

import org.example.apirest.dto.notification.NotificationDto;
import org.example.apirest.dto.notification.CreateNotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> findAll();
    NotificationDto findOne(Long id);
    NotificationDto save(CreateNotificationDto notification);
    NotificationDto update(Long id, CreateNotificationDto notification);
    void delete(Long id);
}
