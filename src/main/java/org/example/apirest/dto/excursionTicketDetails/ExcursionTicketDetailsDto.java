package org.example.apirest.dto.excursionTicketDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcursionTicketDetailsDto extends BaseDto {
    private Integer price;
    private Integer availableSpaces;
    private Long excursionId;
    private Long routeId;
    private Long horaryId;
}
