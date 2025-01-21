package org.example.apirest.dto.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessDto extends BaseDto {
    private String name;
    private String documentationUrl;
    private String contactNumber;
    private String text;
}
