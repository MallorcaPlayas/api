package org.example.apirest.repository;

import org.example.apirest.model.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {
}
