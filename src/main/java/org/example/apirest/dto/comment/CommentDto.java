package org.example.apirest.dto.comment;

import lombok.Data;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.model.Beach;

import java.util.Date;

@Data
public class CommentDto{
    private Long id;
    private Date editedDate;
    private Integer rating;
    private String comment;
    private Date publishedDate;
    private BeachDto beach;
}
