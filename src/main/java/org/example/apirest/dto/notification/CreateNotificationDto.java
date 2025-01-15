package org.example.apirest.dto.notification;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateNotificationDto extends BaseCreateDto {
    private String subject;
    private String description;
    private boolean read;
}
