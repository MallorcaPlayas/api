//package org.example.apirest.service.notification;
//
//import lombok.RequiredArgsConstructor;
//import org.example.apirest.dto.notification.NotificationDto;
//import org.example.apirest.dto.notification.CreateNotificationDto;
//import org.example.apirest.error.NotFoundException;
//import org.example.apirest.model.Notification;
//import org.example.apirest.repository.NotificationRepository;
//import org.example.apirest.service.DtoConverter;
//import org.example.apirest.utils.UtilsClass;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class NotificationServiceImpl implements DtoConverter<Notification, NotificationDto, CreateNotificationDto> {
//
//    private final NotificationRepository repository;
//    private final ModelMapper modelMapper;
//
//    public List<NotificationDto> findAll() {
//        return this.toDtoList(repository.findAll());
//    }
//
//    public NotificationDto findOne(Long id) {
//        Notification notification = repository.findById(id)
//                .orElseThrow(() -> new NotFoundException(Notification.class, id));
//        return this.toDto(notification);
//    }
//
//    public NotificationDto save(CreateNotificationDto createNotificationDto) {
//        Notification notification = fromDto(createNotificationDto);
//        Notification savedNotification = repository.save(notification);
//        return toDto(savedNotification);
//    }
//
//    public NotificationDto update(Long id, CreateNotificationDto createNotificationDto) {
//        Notification oldNotification = repository.findById(id)
//                .orElseThrow(() -> new NotFoundException(Notification.class, id));
//        Notification newNotification = fromDto(createNotificationDto);
//
//        if (newNotification == null) {
//            return null;
//        }
//
//        UtilsClass.updateFields(oldNotification, newNotification);
//
//        Notification savedNotification = repository.save(oldNotification);
//        return toDto(savedNotification);
//    }
//
//    public void delete(Long id) {
//        Notification notification = repository.findById(id)
//                .orElseThrow(() -> new NotFoundException(Notification.class, id));
//        repository.delete(notification);
//    }
//
//    @Override
//    public NotificationDto toDto(Notification notification) {
//        return modelMapper.map(notification, NotificationDto.class);
//    }
//
//    @Override
//    public List<NotificationDto> toDtoList(List<Notification> notifications) {
//        return notifications.stream()
//                .map(this::toDto)
//                .toList();
//    }
//
//    @Override
//    public Notification fromDto(CreateNotificationDto createNotificationDto) {
//        return modelMapper.map(createNotificationDto, Notification.class);
//    }
//
//    @Override
//    public List<Notification> fromDtoList(List<CreateNotificationDto> createNotificationDtos) {
//        return createNotificationDtos.stream()
//                .map(this::fromDto)
//                .toList();
//    }
//}
