package org.example.apirest.dto.bill;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CreateBillDto{
    private String name;
    private String userName;
    private String firstSurname;
    private String secondSurname;
    private LocalDateTime birthDate;
    private String email;
    private Double amount;
    private Date amountDate;
    private Long billTypeId;
}
