package org.example.apirest.dto.beach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.BeachHasService;
import org.example.apirest.model.User;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BeachDto extends BaseDto {
    private String name;
    private String description;
    private List<TypeBeachDto> types;
    private List <CameraDto> cameras;
    private List<UserDto> usersInCharge;
    @JsonProperty("services")
    private List<BeachHasServiceDto> beachHasServiceBeach;
}
