package org.example.apirest.dto.organization;

import lombok.Data;

@Data
public class CreateOrganizationDto{
    private Long id;
    private String documentationUrl;
    private String contactNumber;
}
