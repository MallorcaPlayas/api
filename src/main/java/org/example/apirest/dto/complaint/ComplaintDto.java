package org.example.apirest.dto.complaint;

import lombok.Data;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.user.UserDto;

@Data
public class ComplaintDto{
    private Long id;
    private String message;
    private String status;
    private String date;
    private BeachDto beach;
    private RouteDto route;
    private UserDto user;
}
