package org.example.apirest.dto.pointOfInterestType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class PointOfInterestTypeDto extends BaseDto {
    private String name;
}
