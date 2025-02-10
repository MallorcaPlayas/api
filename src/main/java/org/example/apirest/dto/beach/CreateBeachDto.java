package org.example.apirest.dto.beach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateBeachDto extends BaseCreateDto {
    private String description;
    private List<TypeBeachDto> types;
    private List <CreateCameraDto> cameras;
    private List<CreateBeachManagerDto> usersInCharge;
    @JsonProperty("services")
    private List<CreateBeachHasServiceDto> beachHasServiceBeach;
    private CreateLocationDto location;
    private List<CreatePhotoDto> photos;
}
