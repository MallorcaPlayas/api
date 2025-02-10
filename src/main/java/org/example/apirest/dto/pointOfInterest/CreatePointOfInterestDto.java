package org.example.apirest.dto.pointOfInterest;

import lombok.Data;

@Data
public class CreatePointOfInterestDto{
    private Long id;
    private String description;
    private String url;
    private Long pointOfInterestId;
}
