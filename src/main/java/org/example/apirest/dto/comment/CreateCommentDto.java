package org.example.apirest.dto.comment;

import lombok.Data;

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
