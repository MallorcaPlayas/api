package org.example.apirest.dto.photo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class PhotoDto extends BaseDto {
    private Long beachId;
    private Long routeId;
    private Long userId;
    private Long excursionId;
    private Long complaintId;
    @JsonProperty("private")
    private boolean isPrivate;
    private String url;
    private Long commentId;
}
