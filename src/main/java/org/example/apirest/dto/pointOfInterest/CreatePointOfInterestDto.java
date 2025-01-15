package org.example.apirest.dto.pointOfInterest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreatePointOfInterestDto extends BaseCreateDto {
    private String description;
    private String url;
}
