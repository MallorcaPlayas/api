package org.example.apirest.dto.organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrganizationDto extends BaseDto {
    private String documentationUrl;
    private String contactNumber;
}
