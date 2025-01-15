package org.example.apirest.dto.comment;

import lombok.Data;
import org.example.apirest.dto.BaseCreateDto;

import java.util.Date;

@Data
public class CreateCommentDto{
    private Date editedDate;
    private Integer rating;
    private String comment;
    private Date publishedDate;
}
