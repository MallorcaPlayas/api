package org.example.apirest.dto.budgetBusiness;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BudgetBusinessDto extends BaseDto {
    private Long budgetId;
    private Long businessId;
    private boolean paid;
    private Date proposalDate;
    private Date paidDate;
    private Date endDate;
}
