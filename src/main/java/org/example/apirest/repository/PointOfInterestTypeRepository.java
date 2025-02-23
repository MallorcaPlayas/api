package org.example.apirest.repository;

import org.example.apirest.model.PointOfInterestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointOfInterestTypeRepository extends JpaRepository<PointOfInterestType, Long> {
}
