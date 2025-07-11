package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.route.Route;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date editedDate;

    private Integer rating;
    private String content;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date publishedDate;

    @ManyToOne
    @JoinColumn(name = "beach_id")
    private Beach beach;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany
//    private List<Photo> photos;
}
