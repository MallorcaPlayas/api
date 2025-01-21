package org.example.apirest.dto.excursionTicketDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateExcursionTicketDetailsDto extends BaseCreateDto {
    private Integer price;
    private Integer availableSpaces;
    private Long excursionId;
    private Long routeId;
    private Long horaryId;
}
