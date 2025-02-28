package org.example.apirest.dto.route;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateRouteDto{
    private String name;
    private Double distance;
    private Double duration;
    private Double elevationAsc;
    private Double elevationDesc;
    private Boolean isPrivate;
    private MultipartFile file;
    private Long userId;
}
