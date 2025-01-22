package org.example.apirest.dto.segment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.model.Location;

@EqualsAndHashCode(callSuper = true)
@Data
public class SegmentDto extends BaseDto {
    private Location locationOne;
    private Location locationTwo;
}
