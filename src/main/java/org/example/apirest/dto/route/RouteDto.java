package org.example.apirest.dto.route;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class RouteDto extends BaseDto {
    private double distance;
    private double duration;
    private double elevation;
    private boolean isPrivate;
    private Long userId;
}
