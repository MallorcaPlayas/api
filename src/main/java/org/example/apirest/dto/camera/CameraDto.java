package org.example.apirest.dto.camera;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class CameraDto extends BaseDto {
    private String url;
}
