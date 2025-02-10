package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.cypherdsl.core.Use;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bucket;

    private String path;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

//    @ManyToOne
//    @JoinColumn(name = "comment_id")
//    private Comment comment;
//
//    @OneToOne
//    @JoinColumn(name="user_id")
//    private User user;
}
