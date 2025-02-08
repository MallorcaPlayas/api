package org.example.apirest.dto.complaint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.user.UserDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class ComplaintDto extends BaseDto {
    private String message;
    private String status;
    private String date;
    private BeachDto beach;
    private RouteDto route;
    private UserDto user;
}
