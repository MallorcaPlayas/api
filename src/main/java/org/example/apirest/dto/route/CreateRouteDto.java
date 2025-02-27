package org.example.apirest.dto.route;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.location.CreateLocationDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateRouteDto extends BaseCreateDto {
    private String name;
    private Double distance;
    private Double duration;
    private Double elevationAsc;
    private Double elevationDesc;
    private Boolean isPrivate;
    private List<CreateLocationDto> locations;
    private Long userId;
}
