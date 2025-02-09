package org.example.apirest.dto.photo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class PhotoDto extends BaseDto {
    private Long id;
    private String url;
}
