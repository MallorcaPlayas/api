package org.example.apirest.dto.budgetBusiness;

import lombok.Data;

import java.util.Date;

@Data
public class CreateBudgetBusinessDto {
    private Long budgetId;
    private Long businessId;
    private boolean paid;
    private Date proposalDate;
    private Date paidDate;
    private Date endDate;
}
