package org.example.apirest.dto.pointOfInterest;

import lombok.Data;

@Data
public class PointOfInterestDto{
    private Long id;
    private String name;
    private String description;
    private String url;
    private Long pointOfInterestId;
}
