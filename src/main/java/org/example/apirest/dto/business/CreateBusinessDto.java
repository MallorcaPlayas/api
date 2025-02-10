package org.example.apirest.dto.business;

import lombok.Data;

@Data
public class CreateBusinessDto{
    private String name;
    private String documentationUrl;
    private String contactNumber;
    private String text;
    private Long businessTypeId;
}
