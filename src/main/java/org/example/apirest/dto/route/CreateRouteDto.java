package org.example.apirest.dto.route;

import lombok.Data;
import org.example.apirest.dto.location.CreateLocationDto;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateRouteDto{
    private String name;
    private double distance;
    private double duration;
    private double elevationAsc;
    private double elevationDesc;
    private boolean isPrivate;
    private MultipartFile file;
    private Long userId;
}
