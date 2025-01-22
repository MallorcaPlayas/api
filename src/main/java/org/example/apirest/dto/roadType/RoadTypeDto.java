package org.example.apirest.dto.roadType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoadTypeDto  extends BaseDto {
    String name;
}
