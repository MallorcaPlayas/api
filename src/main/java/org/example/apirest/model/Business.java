package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "businesses")
public class Business implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String documentationUrl;
    private String contactNumber;
    private String text;

    @OneToMany(mappedBy = "business")
    private List<BusinessHorary> businessHoraries;

    @ManyToOne
    @JoinColumn(name = "business_type_id")
    private BusinessType businessType;
}
