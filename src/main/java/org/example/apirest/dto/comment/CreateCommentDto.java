package org.example.apirest.dto.comment;

import lombok.Data;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.route.RouteDto;

import java.util.Date;

@Data
public class CreateCommentDto{
    private Date editedDate;
    private Integer rating;
    private String comment;
    private Date publishedDate;
    private Long beachId;
    private Long routeId;
    private Long userId;
}
