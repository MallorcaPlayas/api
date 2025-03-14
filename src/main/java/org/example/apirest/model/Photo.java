package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirest.model.beach.Beach;
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

    @Column(name = "private")
    private boolean isPrivate;

    private String url;

    @ManyToOne
    @JoinColumn(name = "beach_id")
    private Beach beach;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;

    @OneToOne
    @JoinColumn(name="excursion_id")
    private Excursion excursion;

    @ManyToOne
    @JoinColumn(name="complaint_id")
    private Complaint complaint;
}
