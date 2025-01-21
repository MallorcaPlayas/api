package org.example.apirest.dto.bill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BillDto extends BaseDto {
    private String userName;
    private String firstSurname;
    private String secondSurname;
    private Date birthDate;
    private String email;
    private Double amount;
    private Date amountDate;
}
