package org.example.apirest.dto.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateBusinessDto extends BaseCreateDto {
    private String documentationUrl;
    private String contactNumber;
    private String text;
    private Long businessTypeId;
}
