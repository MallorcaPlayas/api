package org.example.apirest.dto.aggregationType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateAggregationTypeDto extends BaseCreateDto {
    private String name;
}
