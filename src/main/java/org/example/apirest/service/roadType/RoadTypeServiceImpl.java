package org.example.apirest.service.roadType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.roadType.RoadTypeDto;
import org.example.apirest.dto.roadType.CreateRoadTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.RoadType;
import org.example.apirest.repository.RoadTypeRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoadTypeServiceImpl implements DtoConverter<RoadType, RoadTypeDto, CreateRoadTypeDto> {

    private final RoadTypeRepository repository;
    private final ModelMapper mapper;

    public List<RoadTypeDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public RoadTypeDto findOne(Long id) {
        RoadType roadType = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RoadType.class, id));
        return this.toDto(roadType);
    }

    public RoadTypeDto save(CreateRoadTypeDto createRoadTypeDto) {
        RoadType roadType = fromDto(createRoadTypeDto);
        RoadType savedRoadType = repository.save(roadType);
        return toDto(savedRoadType);
    }

    public RoadTypeDto update(Long id, CreateRoadTypeDto createRoadTypeDto) {
        RoadType oldRoadType = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RoadType.class, id));
        RoadType roadTypeToUpdate = fromDto(createRoadTypeDto);

        UtilsClass.updateFields(oldRoadType, roadTypeToUpdate);

        RoadType savedRoadType = repository.save(oldRoadType);
        return toDto(savedRoadType);
    }

    public void delete(Long id) {
        RoadType roadType = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RoadType.class, id));
        repository.delete(roadType);
    }

    @Override
    public RoadTypeDto toDto(RoadType roadType) {
        return mapper.map(roadType, RoadTypeDto.class);
    }

    @Override
    public List<RoadTypeDto> toDtoList(List<RoadType> roadTypes) {
        return roadTypes.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public RoadType fromDto(CreateRoadTypeDto createRoadTypeDto) {
        return mapper.map(createRoadTypeDto, RoadType.class);
    }

    @Override
    public List<RoadType> fromDtoList(List<CreateRoadTypeDto> createRoadTypeDtos) {
        return createRoadTypeDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
