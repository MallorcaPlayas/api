package org.example.apirest.dto.organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrganizationDto extends BaseCreateDto {
//    private String documentationUrl;
    private String contactNumber;
}
