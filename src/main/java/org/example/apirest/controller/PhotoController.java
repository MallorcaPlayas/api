package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.controller.validators.Validator;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.Photo;
import org.example.apirest.security.JwtService;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.example.apirest.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoServiceImpl service;
    private final Validator<CreatePhotoDto> photoValidator;
    private final UserServiceImpl userService;

    @PostMapping(consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoDto> create(@ModelAttribute CreatePhotoDto entity) {
        photoValidator.validate(entity);
        PhotoDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id , @AuthenticationPrincipal UserDetails userDetails) {

        PhotoDto photoDto = service.findOne(id);

        if(photoDto == null) return;

        if(!photoDto.isPrivate()) service.delete(id);

        String username = userDetails.getUsername();
        UserDto userDto = userService.findByUserName(username).orElse(null);

        if(!photoDto.getUserId().equals(userDto.getId())) return;

        service.delete(id);
    }
}
