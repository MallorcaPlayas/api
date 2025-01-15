package org.example.apirest.dto.notification;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotificationDto extends BaseDto {
    private String subject;
    private String description;
    private boolean read;
}
