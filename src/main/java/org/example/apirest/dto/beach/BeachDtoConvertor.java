package org.example.apirest.dto.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.model.Beach;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BeachDtoConvertor  implements DtoConverter<Beach,BeachDto,CreateBeachDto> {
    private final ModelMapper modelMapper;

    public BeachDto convertDto(Beach beach) {
        return modelMapper.map(beach, BeachDto.class);
    }

    public List<BeachDto> convertDtoList(List<Beach> beachList) {
        return beachList.stream().map(this::convertDto).toList();
    }

    public Beach convertToEntityFromDto(BeachDto beachDto) {
        return modelMapper.map(beachDto, Beach.class);
    }

    public Beach convertToEntityFromCreateDto(CreateBeachDto createBeachDto) {
        Beach beach = modelMapper.map(createBeachDto, Beach.class);
        beach.setId(null);
        return beach;
    }

    public List<Beach> convertToEntityListFromCreateDto(List<CreateBeachDto> beachDtos) {
        return beachDtos.stream().map(this::convertToEntityFromCreateDto).toList();
    }
}