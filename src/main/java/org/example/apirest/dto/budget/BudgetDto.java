package org.example.apirest.dto.budget;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class BudgetDto extends BaseDto {
    private Double price;
}
