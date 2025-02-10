package org.example.apirest.dto.beachManager;

import lombok.Data;
import org.example.apirest.dto.user.UserDto;

@Data
public class CreateBeachManagerDto {
    private String name;
    private Long beach_id;
    private UserDto user;
}
