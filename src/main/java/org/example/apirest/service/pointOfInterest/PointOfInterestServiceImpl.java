package org.example.apirest.service.pointOfInterest;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.PointOfInterest;
import org.example.apirest.repository.PointOfInterestRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointOfInterestServiceImpl implements DtoConverter<PointOfInterest, PointOfInterestDto, CreatePointOfInterestDto> {

    private final PointOfInterestRepository repository;
    private final ModelMapper mapper;

    public List<PointOfInterestDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public PointOfInterestDto findOne(Long id) {
        PointOfInterest pointOfInterest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PointOfInterest.class, id));
        return this.toDto(pointOfInterest);
    }

    public PointOfInterestDto save(CreatePointOfInterestDto createPointOfInterestDto) {
        PointOfInterest pointOfInterest = fromDto(createPointOfInterestDto);
        PointOfInterest savedPointOfInterest = repository.save(pointOfInterest);
        return toDto(savedPointOfInterest);
    }

    public PointOfInterestDto update(Long id, CreatePointOfInterestDto createPointOfInterestDto) {
        PointOfInterest oldPointOfInterest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PointOfInterest.class, id));
        PointOfInterest pointOfInterestToUpdate = fromDto(createPointOfInterestDto);

        UtilsClass.updateFields(oldPointOfInterest, pointOfInterestToUpdate);

        PointOfInterest savedPointOfInterest = repository.save(oldPointOfInterest);
        return toDto(savedPointOfInterest);
    }

    public void delete(Long id) {
        PointOfInterest pointOfInterest = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PointOfInterest.class, id));
        repository.delete(pointOfInterest);
    }

    @Override
    public PointOfInterestDto toDto(PointOfInterest pointOfInterest) {
        return mapper.map(pointOfInterest, PointOfInterestDto.class);
    }

    @Override
    public List<PointOfInterestDto> toDtoList(List<PointOfInterest> pointOfInterests) {
        return pointOfInterests.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public PointOfInterest fromDto(CreatePointOfInterestDto createPointOfInterestDto) {
        return mapper.map(createPointOfInterestDto, PointOfInterest.class);
    }

    @Override
    public List<PointOfInterest> fromDtoList(List<CreatePointOfInterestDto> createPointOfInterestDtos) {
        return createPointOfInterestDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
