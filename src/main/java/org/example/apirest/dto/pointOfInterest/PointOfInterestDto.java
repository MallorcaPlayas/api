package org.example.apirest.dto.pointOfInterest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class PointOfInterestDto extends BaseDto {
    private String name;
    private String description;
    private String url;
}
