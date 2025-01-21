package org.example.apirest.dto.typeBeach;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;


@EqualsAndHashCode(callSuper = true)
@Data
public class TypeBeachDto extends BaseDto {
    private String name;
}
