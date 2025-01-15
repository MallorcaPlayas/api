package org.example.apirest.dto.budget;

import lombok.Data;
import org.example.apirest.dto.BaseDto;

@Data
public class BudgetDto{
    private Long id;
    private Double price;
}
