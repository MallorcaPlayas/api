package org.example.apirest.dto.beachManager;

import lombok.Data;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.User;

import java.time.LocalTime;

@Data
public class CreateBeachManagerDto {
    private Long beach_id;
    private UserDto user;
}
