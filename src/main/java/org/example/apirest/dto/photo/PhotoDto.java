package org.example.apirest.dto.photo;

import lombok.Data;
import org.example.apirest.dto.BaseDto;

@Data
public class PhotoDto{
    private Long id;
    private String url;
}
