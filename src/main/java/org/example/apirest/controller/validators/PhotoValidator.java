package org.example.apirest.controller.validators;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class PhotoValidator implements Validator{

    private final Validator fileValidator;

    @Override
    public boolean validate(Predicate<Object[]> callBack, Object... createPhotoDtos) {
        return this.validate(createPhotoDtos) && callBack.test(createPhotoDtos);
    }

    @Override
    public boolean validate(Object... objects) {
        List<CreatePhotoDto> createPhotoDtos = Arrays.stream(objects).map(o -> (CreatePhotoDto) o).toList();
        return createPhotoDtos.stream()
                .allMatch(createPhotoDto ->
                        assignmentValidate(createPhotoDto) &&
                        fileValidator.validate(createPhotoDto.getFile())
                );
    }

    private boolean assignmentValidate(CreatePhotoDto createPhotoDto){
        boolean beachAssigned = createPhotoDto.getBeachId() != null;
        boolean routeAssigned = createPhotoDto.getRouteId() != null;
        boolean excursionAssigned = createPhotoDto.getExcursionId() != null;
        boolean userAssigned = createPhotoDto.getUserId() != null;
        boolean commentAssigned = createPhotoDto.getCommentId() != null;

        return beachAssigned || routeAssigned || excursionAssigned || userAssigned || commentAssigned;
    }
}
