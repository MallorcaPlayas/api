package org.example.apirest.dto.beach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BeachDto extends BaseDto {
    private String name;
    private String description;
    private List<TypeBeachDto> types;
    private List <CameraDto> cameras;
    private List<BeachManagerDto> usersInCharge;
    @JsonProperty("services")
    private List<BeachHasServiceDto> beachHasServiceBeach;
    private List<PhotoDto> photos;
//    private LocationDto location;
}
