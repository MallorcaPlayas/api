package org.example.apirest.service.notification;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.notification.NotificationDto;
import org.example.apirest.dto.notification.CreateNotificationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Notification;
import org.example.apirest.repository.NotificationRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final DtoConverterImpl<Notification, NotificationDto, CreateNotificationDto> dtoConverter;

    @Override
    public List<NotificationDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), NotificationDto.class);
    }

    @Override
    public NotificationDto findOne(Long id) {
        Notification notification = repository.findById(id).orElseThrow(() -> new NotFoundException(Notification.class, id));
        return dtoConverter.convertDto(notification, NotificationDto.class);
    }

    @Override
    public NotificationDto save(CreateNotificationDto notification) {
        Notification notificationToInsert = dtoConverter.convertToEntityFromCreateDto(notification, Notification.class);
        return dtoConverter.convertDto(repository.save(notificationToInsert), NotificationDto.class);
    }

    @Override
    public NotificationDto update(Long id, CreateNotificationDto notification) {
        Notification oldNotification = repository.findById(id).orElseThrow(() -> new NotFoundException(Notification.class, id));
        Notification notificationToInsert = dtoConverter.convertToEntityFromCreateDto(notification, Notification.class);

        if (oldNotification == null) {
            return null;
        }

        UtilsClass.updateFields(oldNotification, notificationToInsert);

        return dtoConverter.convertDto(repository.save(oldNotification), NotificationDto.class);
    }

    @Override
    public void delete(Long id) {
        Notification notification = repository.findById(id).orElseThrow(() -> new NotFoundException(Notification.class, id));
        repository.delete(notification);
    }
}
