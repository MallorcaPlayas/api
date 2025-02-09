package org.example.apirest.dto.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDto extends BaseDto {
    private Date editedDate;
    private Integer rating;
    private String comment;
    private Date publishedDate;
    private Long beachId;
    private Long routeId;
    private Long userId;
}
