package org.example.apirest.service.notification;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.notification.NotificationDto;
import org.example.apirest.dto.notification.CreateNotificationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Location;
import org.example.apirest.model.Notification;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.repository.NotificationRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl extends GeneralizedServiceImpl<Notification, NotificationDto, CreateNotificationDto, NotificationRepository> {
    public NotificationServiceImpl(NotificationRepository repository, DtoConverterImpl<Notification,NotificationDto,CreateNotificationDto> dtoConverter) {
        super(repository, dtoConverter, Notification.class, NotificationDto.class);
    }
}
