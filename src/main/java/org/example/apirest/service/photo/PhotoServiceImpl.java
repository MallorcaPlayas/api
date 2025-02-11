package org.example.apirest.service.photo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Photo;
import org.example.apirest.repository.PhotoRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.service.s3.S3Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements DtoConverter<Photo,PhotoDto,CreatePhotoDto> {

    private static final String PUBLIC_BUCKET = "mallorca-playas-public";
    private static final String PRIVATE_BUCKET = "mallorca-playas-private";

    private final S3Service s3Service;
    private final PhotoRepository repository;
    private final ModelMapper mapper;

    public List<PhotoDto> findAll() {
        List<Photo> photos = repository.findAll();
        return toDtoList(photos);
    }

    public PhotoDto findOne(Long id) {
        Photo photo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Photo.class, id));
        return toDto(photo);
    }

    @SneakyThrows
    public PhotoDto save(CreatePhotoDto createPhotoDto) {
        Photo photo = this.fromDto(createPhotoDto);

        s3Service.uploadFile(photo.getBucket(), photo.getPath(), createPhotoDto.getFile());

        Photo savedPhoto = repository.save(photo);

        return this.toDto(savedPhoto);
    }

//    public PhotoDto update(UpdatePhotoDto dto) {
//        Photo photo = repository.findById(dto.getId())
//                .orElseThrow(() -> new NotFoundException(Photo.class, dto.getId()));
//
//        Photo updatedPhoto = mapper.map(dto, Photo.class);
//
//        UtilsClass.updateFields(photo, updatedPhoto);
//
//        Photo savedPhoto = repository.save(updatedPhoto);
//        return toPhotoDto(savedPhoto);
//    }

    public void delete(Long id) {
        Photo photo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Photo.class, id));

        s3Service.deleteFile(photo.getBucket(),photo.getPath());

        repository.delete(photo);
    }

    private String getBucket(boolean isPerivate) {
        return isPerivate ? PRIVATE_BUCKET : PUBLIC_BUCKET;
    }

    @Override
    public PhotoDto toDto(Photo photo) {
        PhotoDto photoDto = mapper.map(photo, PhotoDto.class);

        if (photo.getIsPrivate()) {
            String url = s3Service.generateTemporalUrl(photo.getBucket(), photo.getPath(), 10L);
            photoDto.setUrl(url);
        }

        return photoDto;
    }

    @Override
    public List<PhotoDto> toDtoList(List<Photo> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Photo fromDto(CreatePhotoDto createPhotoDto) {
        Photo photo = mapper.map(createPhotoDto, Photo.class);

        photo.setBucket(this.getBucket(photo.getIsPrivate()));

        String url = null;

        if(!photo.getIsPrivate()) url = s3Service.generateUrl(photo.getBucket(), photo.getPath());

        photo.setUrl(url);

        return photo;
    }

    @Override
    public List<Photo> fromDtoList(List<CreatePhotoDto> createPhotoDtos) {
        return createPhotoDtos.stream().map(this::fromDto).toList();
    }
}

