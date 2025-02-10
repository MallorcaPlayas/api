package org.example.apirest.dto.beach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;

import java.util.List;

@Data
public class BeachDto{
    private Long id;
    private String name;
    private String description;
    private List<TypeBeachDto> types;
    private List <CameraDto> cameras;
    private List<BeachManagerDto> usersInCharge;
    @JsonProperty("services")
    private List<BeachHasServiceDto> beachHasServiceBeach;
    private List<PhotoDto> photos;
    private LocationDto location;
}
