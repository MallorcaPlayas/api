package org.example.apirest.dto.photo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreatePhotoDto{
    private MultipartFile file;

}
