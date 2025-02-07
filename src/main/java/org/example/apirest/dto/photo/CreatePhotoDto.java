package org.example.apirest.dto.photo;

import lombok.Data;

@Data
public class CreatePhotoDto{
    private String bucket;
    private String url;
}
