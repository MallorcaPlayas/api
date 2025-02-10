package org.example.apirest.dto.beachManager;

import lombok.Data;
import org.example.apirest.dto.user.UserDto;

@Data
public class BeachManagerDto{
    private Long id;
    private UserDto user;
}
