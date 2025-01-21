package org.example.apirest.dto.billType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class BillTypeDto extends BaseDto {
    private String name;
}
