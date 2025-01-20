package org.example.apirest.dto.beachManager;

import lombok.Data;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.User;

import java.time.LocalTime;

@Data
public class BeachManagerDto {
    private Long id;
    private BeachDto beach;
    private UserDto user;
}
