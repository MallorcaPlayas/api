package org.example.apirest.controller.validators;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.error.photo_exceptions.PhotoNotAssignedException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class PhotoValidator implements Validator<CreatePhotoDto>{

    private final Validator<MultipartFile> fileValidator;

    @Override
    public void validate(CreatePhotoDto createPhotoDto) {
        assignmentValidate(createPhotoDto);
        fileValidator.validate(createPhotoDto.getFile());
    }

    private void assignmentValidate(CreatePhotoDto createPhotoDto){
        boolean beachAssigned = createPhotoDto.getBeachId() != null;
        boolean routeAssigned = createPhotoDto.getRouteId() != null;
        boolean excursionAssigned = createPhotoDto.getExcursionId() != null;
        boolean userAssigned = createPhotoDto.getUserId() != null;
        boolean commentAssigned = createPhotoDto.getCommentId() != null;

        if(!beachAssigned && !routeAssigned && !excursionAssigned && !userAssigned && !commentAssigned) {
            throw new PhotoNotAssignedException();
        }
    }
}
