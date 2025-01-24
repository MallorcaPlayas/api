package org.example.apirest.dto.beachManager;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.User;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class BeachManagerDto extends BaseDto {
    private UserDto user;
}
