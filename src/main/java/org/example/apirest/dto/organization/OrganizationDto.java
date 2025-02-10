package org.example.apirest.dto.organization;

import lombok.Data;

@Data
public class OrganizationDto{
    private Long id;
    private String name;
    private String documentationUrl;
    private String contactNumber;
}
