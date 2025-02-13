package org.example.apirest.config;

import org.example.apirest.controller.validators.FileValidator;
import org.example.apirest.controller.validators.PhotoValidator;
import org.example.apirest.controller.validators.Validator;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator<MultipartFile> fileValidator(){
        final List<String> ALLOWED_MIME = List.of("image/png", "image/jpeg");
        final List<String> ALLOWED_EXTENSION = List.of(".png", ".jpeg",".jpg");
        return new FileValidator(ALLOWED_MIME,ALLOWED_EXTENSION,null,null);
    }

    @Bean
    public Validator<CreatePhotoDto> photoValidator(){
        return new PhotoValidator(this.fileValidator());
    }

}
