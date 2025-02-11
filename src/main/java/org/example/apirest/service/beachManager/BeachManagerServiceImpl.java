package org.example.apirest.service.beachManager;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Beach;
import org.example.apirest.model.BeachManager;
import org.example.apirest.model.User;
import org.example.apirest.repository.BeachManagerRepository;
import org.example.apirest.repository.BeachRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachManagerServiceImpl implements DtoConverter<BeachManager, BeachManagerDto, CreateBeachManagerDto> {

    private final BeachManagerRepository repository;
    private final BeachRepository beachRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<BeachManagerDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public BeachManagerDto findOne(Long id) {
        BeachManager beachManager = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BeachManager.class, id));
        return this.toDto(beachManager);
    }

    public BeachManagerDto save(CreateBeachManagerDto createBeachManagerDto) {
        BeachManager beachManager = fromDto(createBeachManagerDto);

        Beach beach = beachRepository.findById(createBeachManagerDto.getBeach_id())
                .orElseThrow(() -> new NotFoundException(Beach.class, createBeachManagerDto.getBeach_id()));
        User user = userRepository.findById(createBeachManagerDto.getUser().getId())
                .orElseThrow(() -> new NotFoundException(User.class, createBeachManagerDto.getUser().getId()));

        beachManager.setBeach(beach);
        beachManager.setUser(user);

        BeachManager savedBeachManager = repository.save(beachManager);
        return toDto(savedBeachManager);
    }

    public BeachManagerDto update(Long id, CreateBeachManagerDto createBeachManagerDto) {
        BeachManager oldBeachManager = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BeachManager.class, id));
        BeachManager newBeachManager = fromDto(createBeachManagerDto);

        UtilsClass.updateFields(oldBeachManager, newBeachManager);

        Beach beach = beachRepository.findById(createBeachManagerDto.getBeach_id())
                .orElseThrow(() -> new NotFoundException(Beach.class, createBeachManagerDto.getBeach_id()));
        User user = userRepository.findById(createBeachManagerDto.getUser().getId())
                .orElseThrow(() -> new NotFoundException(User.class, createBeachManagerDto.getUser().getId()));

        oldBeachManager.setBeach(beach);
        oldBeachManager.setUser(user);

        BeachManager savedBeachManager = repository.save(oldBeachManager);
        return toDto(savedBeachManager);
    }

    public void delete(Long id) {
        BeachManager beachManager = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BeachManager.class, id));
        repository.delete(beachManager);
    }

    @Override
    public BeachManagerDto toDto(BeachManager beachManager) {
        return modelMapper.map(beachManager, BeachManagerDto.class);
    }

    @Override
    public List<BeachManagerDto> toDtoList(List<BeachManager> beachManagers) {
        return beachManagers.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public BeachManager fromDto(CreateBeachManagerDto createBeachManagerDto) {
        return modelMapper.map(createBeachManagerDto, BeachManager.class);
    }

    @Override
    public List<BeachManager> fromDtoList(List<CreateBeachManagerDto> createBeachManagerDtos) {
        return createBeachManagerDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
