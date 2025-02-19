package org.example.apirest.service.notification;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.notification.NotificationDto;
import org.example.apirest.dto.notification.CreateNotificationDto;
import org.example.apirest.model.Notification;
import org.example.apirest.repository.NotificationRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl extends GeneralizedServiceImpl<Notification, NotificationDto, CreateNotificationDto, NotificationRepository> {
    public NotificationServiceImpl(NotificationRepository repository, DtoConverterGeneralizedImpl<Notification,NotificationDto,CreateNotificationDto> dtoConverter) {
        super(repository, dtoConverter, Notification.class, NotificationDto.class);
    }
}
